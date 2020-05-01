package sr.grpc.server;

import io.grpc.stub.StreamObserver;
import sr.proto.NewsProviderGrpc;
import sr.proto.NewsRequest;
import sr.proto.NewsResponse;

import java.util.concurrent.ConcurrentHashMap;

public class NewsProviderImpl extends NewsProviderGrpc.NewsProviderImplBase {
    private ConcurrentHashMap<Subscription, StreamObserver<NewsResponse>> subscriptions = new ConcurrentHashMap<>();

    public ConcurrentHashMap<Subscription, StreamObserver<NewsResponse>> getSubscribtions() {
        return subscriptions;
    }

    @Override
    public void subscribe(NewsRequest request, StreamObserver<NewsResponse> responseObserver) {
        System.out.println("Nowa subskrypcja od klienta " + request.getClientId() + " na tag " +
                request.getSearchingTag() + " w typie " + request.getType().name());
        Subscription s = new Subscription(request);
        if(subscriptions.containsKey(s)){
            try{
                subscriptions.get(s).onCompleted();
            }catch (IllegalStateException e){
                //it was already closed when client was closed properly
            }catch (io.grpc.StatusRuntimeException e){
                //it was already closed when client was closed properly
            }catch (Exception e){
                e.printStackTrace();
            }
            subscriptions.remove(s);
        }
        subscriptions.put(s, responseObserver);
    }

    @Override
    public void closeClient(sr.proto.ClientName request,
                            io.grpc.stub.StreamObserver<sr.proto.ClientName> responseObserver){
        subscriptions.forEach((s,v) ->{
            if(s.isActive() && s.getClientId().equals(request.getName())) {
                s.setActive(false);
                try{
                    v.onCompleted();
                }catch (IllegalStateException e){
                    //it was already closed when client was closed properly
                }catch (io.grpc.StatusRuntimeException e){
                    //it was already closed when client was closed properly
                }catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println("Closing for " + s.getClientId());
            }
        });

        try {
            responseObserver.onNext(request);
            responseObserver.onCompleted();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void openClient(sr.proto.ClientName request,
                           io.grpc.stub.StreamObserver<sr.proto.NewsRequest> responseObserver) {
        try {
            System.out.println("Opening " + request.getName());
            subscriptions.forEach((k,v)->{
                if (k.getClientId().equals(request.getName())) {
                    System.out.println("Former sub on tag " + k.getTag() + " and type " + k.getType());
                    responseObserver.onNext(k.getNewsRequest());
                }
            });

            responseObserver.onCompleted();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
