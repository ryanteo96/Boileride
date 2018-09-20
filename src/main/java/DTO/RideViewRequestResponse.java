package DTO;

public class RideViewRequestResponse {
    private int result;
    private DtoRideRequest[] requestlist;

    public RideViewRequestResponse(int r, DtoRideRequest[] requestlist) {
        this.result = r;
        this.requestlist = requestlist;
    }

    public int getResult() {
        return result;
    }

    @Anno(name="result")
    public void setResult(int r) {
        result = r;
    }

    public DtoRideRequest[] getRequestlist() {
        return requestlist;
    }

    @Anno(name="requestlist")
    public void setRequestlist(DtoRideRequest[] requestlist) {
        this.requestlist = requestlist;
    }

    public String toString(){
        String str = "result: " + result + ", requestlist: ";
        for (int i=0; i<requestlist.length;i++){
            str += i + ":[" + requestlist[i].toString() + "]";
        }
        return str;
    }
}
