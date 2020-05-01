package sr.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.27.0)",
    comments = "Source: NewsMessages.proto")
public final class NewsProviderGrpc {

  private NewsProviderGrpc() {}

  public static final String SERVICE_NAME = "tutorial.NewsProvider";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<sr.proto.NewsRequest,
      sr.proto.NewsResponse> getSubscribeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "subscribe",
      requestType = sr.proto.NewsRequest.class,
      responseType = sr.proto.NewsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<sr.proto.NewsRequest,
      sr.proto.NewsResponse> getSubscribeMethod() {
    io.grpc.MethodDescriptor<sr.proto.NewsRequest, sr.proto.NewsResponse> getSubscribeMethod;
    if ((getSubscribeMethod = NewsProviderGrpc.getSubscribeMethod) == null) {
      synchronized (NewsProviderGrpc.class) {
        if ((getSubscribeMethod = NewsProviderGrpc.getSubscribeMethod) == null) {
          NewsProviderGrpc.getSubscribeMethod = getSubscribeMethod =
              io.grpc.MethodDescriptor.<sr.proto.NewsRequest, sr.proto.NewsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "subscribe"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.proto.NewsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.proto.NewsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new NewsProviderMethodDescriptorSupplier("subscribe"))
              .build();
        }
      }
    }
    return getSubscribeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sr.proto.ClientName,
      sr.proto.ClientName> getCloseClientMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "closeClient",
      requestType = sr.proto.ClientName.class,
      responseType = sr.proto.ClientName.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sr.proto.ClientName,
      sr.proto.ClientName> getCloseClientMethod() {
    io.grpc.MethodDescriptor<sr.proto.ClientName, sr.proto.ClientName> getCloseClientMethod;
    if ((getCloseClientMethod = NewsProviderGrpc.getCloseClientMethod) == null) {
      synchronized (NewsProviderGrpc.class) {
        if ((getCloseClientMethod = NewsProviderGrpc.getCloseClientMethod) == null) {
          NewsProviderGrpc.getCloseClientMethod = getCloseClientMethod =
              io.grpc.MethodDescriptor.<sr.proto.ClientName, sr.proto.ClientName>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "closeClient"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.proto.ClientName.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.proto.ClientName.getDefaultInstance()))
              .setSchemaDescriptor(new NewsProviderMethodDescriptorSupplier("closeClient"))
              .build();
        }
      }
    }
    return getCloseClientMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sr.proto.ClientName,
      sr.proto.NewsRequest> getOpenClientMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "openClient",
      requestType = sr.proto.ClientName.class,
      responseType = sr.proto.NewsRequest.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<sr.proto.ClientName,
      sr.proto.NewsRequest> getOpenClientMethod() {
    io.grpc.MethodDescriptor<sr.proto.ClientName, sr.proto.NewsRequest> getOpenClientMethod;
    if ((getOpenClientMethod = NewsProviderGrpc.getOpenClientMethod) == null) {
      synchronized (NewsProviderGrpc.class) {
        if ((getOpenClientMethod = NewsProviderGrpc.getOpenClientMethod) == null) {
          NewsProviderGrpc.getOpenClientMethod = getOpenClientMethod =
              io.grpc.MethodDescriptor.<sr.proto.ClientName, sr.proto.NewsRequest>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "openClient"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.proto.ClientName.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sr.proto.NewsRequest.getDefaultInstance()))
              .setSchemaDescriptor(new NewsProviderMethodDescriptorSupplier("openClient"))
              .build();
        }
      }
    }
    return getOpenClientMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static NewsProviderStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<NewsProviderStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<NewsProviderStub>() {
        @java.lang.Override
        public NewsProviderStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new NewsProviderStub(channel, callOptions);
        }
      };
    return NewsProviderStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static NewsProviderBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<NewsProviderBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<NewsProviderBlockingStub>() {
        @java.lang.Override
        public NewsProviderBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new NewsProviderBlockingStub(channel, callOptions);
        }
      };
    return NewsProviderBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static NewsProviderFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<NewsProviderFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<NewsProviderFutureStub>() {
        @java.lang.Override
        public NewsProviderFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new NewsProviderFutureStub(channel, callOptions);
        }
      };
    return NewsProviderFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class NewsProviderImplBase implements io.grpc.BindableService {

    /**
     */
    public void subscribe(sr.proto.NewsRequest request,
        io.grpc.stub.StreamObserver<sr.proto.NewsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSubscribeMethod(), responseObserver);
    }

    /**
     */
    public void closeClient(sr.proto.ClientName request,
        io.grpc.stub.StreamObserver<sr.proto.ClientName> responseObserver) {
      asyncUnimplementedUnaryCall(getCloseClientMethod(), responseObserver);
    }

    /**
     */
    public void openClient(sr.proto.ClientName request,
        io.grpc.stub.StreamObserver<sr.proto.NewsRequest> responseObserver) {
      asyncUnimplementedUnaryCall(getOpenClientMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSubscribeMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                sr.proto.NewsRequest,
                sr.proto.NewsResponse>(
                  this, METHODID_SUBSCRIBE)))
          .addMethod(
            getCloseClientMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                sr.proto.ClientName,
                sr.proto.ClientName>(
                  this, METHODID_CLOSE_CLIENT)))
          .addMethod(
            getOpenClientMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                sr.proto.ClientName,
                sr.proto.NewsRequest>(
                  this, METHODID_OPEN_CLIENT)))
          .build();
    }
  }

  /**
   */
  public static final class NewsProviderStub extends io.grpc.stub.AbstractAsyncStub<NewsProviderStub> {
    private NewsProviderStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NewsProviderStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new NewsProviderStub(channel, callOptions);
    }

    /**
     */
    public void subscribe(sr.proto.NewsRequest request,
        io.grpc.stub.StreamObserver<sr.proto.NewsResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getSubscribeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void closeClient(sr.proto.ClientName request,
        io.grpc.stub.StreamObserver<sr.proto.ClientName> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCloseClientMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void openClient(sr.proto.ClientName request,
        io.grpc.stub.StreamObserver<sr.proto.NewsRequest> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getOpenClientMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class NewsProviderBlockingStub extends io.grpc.stub.AbstractBlockingStub<NewsProviderBlockingStub> {
    private NewsProviderBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NewsProviderBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new NewsProviderBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<sr.proto.NewsResponse> subscribe(
        sr.proto.NewsRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getSubscribeMethod(), getCallOptions(), request);
    }

    /**
     */
    public sr.proto.ClientName closeClient(sr.proto.ClientName request) {
      return blockingUnaryCall(
          getChannel(), getCloseClientMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<sr.proto.NewsRequest> openClient(
        sr.proto.ClientName request) {
      return blockingServerStreamingCall(
          getChannel(), getOpenClientMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class NewsProviderFutureStub extends io.grpc.stub.AbstractFutureStub<NewsProviderFutureStub> {
    private NewsProviderFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NewsProviderFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new NewsProviderFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<sr.proto.ClientName> closeClient(
        sr.proto.ClientName request) {
      return futureUnaryCall(
          getChannel().newCall(getCloseClientMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SUBSCRIBE = 0;
  private static final int METHODID_CLOSE_CLIENT = 1;
  private static final int METHODID_OPEN_CLIENT = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final NewsProviderImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(NewsProviderImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SUBSCRIBE:
          serviceImpl.subscribe((sr.proto.NewsRequest) request,
              (io.grpc.stub.StreamObserver<sr.proto.NewsResponse>) responseObserver);
          break;
        case METHODID_CLOSE_CLIENT:
          serviceImpl.closeClient((sr.proto.ClientName) request,
              (io.grpc.stub.StreamObserver<sr.proto.ClientName>) responseObserver);
          break;
        case METHODID_OPEN_CLIENT:
          serviceImpl.openClient((sr.proto.ClientName) request,
              (io.grpc.stub.StreamObserver<sr.proto.NewsRequest>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class NewsProviderBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    NewsProviderBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return sr.proto.NewsMessages.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("NewsProvider");
    }
  }

  private static final class NewsProviderFileDescriptorSupplier
      extends NewsProviderBaseDescriptorSupplier {
    NewsProviderFileDescriptorSupplier() {}
  }

  private static final class NewsProviderMethodDescriptorSupplier
      extends NewsProviderBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    NewsProviderMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (NewsProviderGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new NewsProviderFileDescriptorSupplier())
              .addMethod(getSubscribeMethod())
              .addMethod(getCloseClientMethod())
              .addMethod(getOpenClientMethod())
              .build();
        }
      }
    }
    return result;
  }
}
