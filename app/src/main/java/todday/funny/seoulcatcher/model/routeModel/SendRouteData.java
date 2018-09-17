package todday.funny.seoulcatcher.model.routeModel;

public class SendRouteData {
    private double startX;
    private double startY;
    private double endX;
    private double endY;
    private String startName;
    private String endName;

    public SendRouteData(double startX, double startY, double endX, double endY, String startName, String endName) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.startName = startName;
        this.endName = endName;
    }
}
