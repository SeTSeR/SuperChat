package accounts;

/**
 * Created by setser on 03.07.17.
 */
public class AccountException extends Exception {
    private AccountError status;

    public AccountException(AccountError status){
        this.status = status;
    }

    public AccountException(Throwable throwable) { super(throwable); }

    public AccountError getStatus() {
        return status;
    }
}
