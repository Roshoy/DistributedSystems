package sr.grpc.server;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import sr.proto.NewsResponse;


public class NewsServer
{
	private static final Logger logger = Logger.getLogger(NewsServer.class.getName());

	private int port = 50051;
	private Server server;
	private ConcurrentHashMap<Subscription, StreamObserver<NewsResponse>> subscriptions;
	private void start() throws IOException 
	{
		NewsProviderImpl newsProvider = new NewsProviderImpl();
		subscriptions = newsProvider.getSubscribtions();
		server = ServerBuilder.forPort(port)
				.addService(newsProvider)
				.build()
				.start();
		logger.info("Server started, listening on " + port);
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				// Use stderr here since the logger may have been reset by its JVM shutdown hook.
				System.err.println("*** shutting down gRPC server since JVM is shutting down");
				NewsServer.this.stop();
				System.err.println("*** server shut down");
			}
		});
	}

	private void stop() {
		if (server != null) {
			server.shutdown();
		}
	}
	/**
	 * Await termination on the main thread since the grpc library uses daemon threads.
	 */
	private void blockUntilShutdown() throws InterruptedException {
		if (server != null) {
			server.awaitTermination();
		}
	}

	private void generateNews() throws InterruptedException{
		while(server != null && !server.isShutdown()){
				Thread.sleep(50);
				sendNewsToSubscribers(NewsGenerator.getNewNews());
		}
	}

	private void sendNewsToSubscribers(NewsResponse news){
		subscriptions.forEach((k,v)->{
			if(k.isActive() && news.getType() == k.getType() && news.getTagsList().contains(k.getTag())){
				try {
					v.onNext(NewsGenerator.getNewNews());
				}catch (io.grpc.StatusRuntimeException ex){
					System.out.println("Closing sub for " + k.getClientId());
					k.setActive(false);
				}
			}
		});
	}

	/**
	 * Main launches the server from the command line.
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		final NewsServer server = new NewsServer();
		server.start();
		server.generateNews();
	}

}
