package pl.marceen.nurseryqueueapi.gdansknurseryteam.entity;

/**
 * @author Marcin Zaremba
 */
public class ConfirmationSucceededEvent {

    private String login;
    private OrderResponse orderResponse;

    public ConfirmationSucceededEvent(String login, OrderResponse orderResponse) {
        this.login = login;
        this.orderResponse = orderResponse;
    }

    public String getLogin() {
        return login;
    }

    public OrderResponse getOrderResponse() {
        return orderResponse;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ConfirmationSucceededEvent{");
        sb.append("login='").append(login).append('\'');
        sb.append(", orderResponse=").append(orderResponse);
        sb.append('}');
        return sb.toString();
    }
}
