package sr.grpc.server;

import sr.proto.ArticleType;
import sr.proto.NewsRequest;

public class Subscription {
    private final NewsRequest request;
    private boolean active;

    public Subscription(NewsRequest request){
        this.request = request;
        active = true;
    }

    public String getTag() {
        return request.getSearchingTag();
    }

    public ArticleType getType() {
        return request.getType();
    }

    public String getClientId() {
        return request.getClientId().getName();
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public NewsRequest getNewsRequest(){
        return request;
    }

    @Override
    public int hashCode(){
        return request.hashCode();
    }

    @Override
    public boolean equals(Object other){
        if(other instanceof Subscription){
            return other.hashCode() == this.hashCode();
        }
        return false;
    }
}
