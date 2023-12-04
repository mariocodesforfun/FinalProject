public class Enum {

    private static final int CHECKING_ACCOUNT_TYPE  = 1;
    private static final int SAVING_ACCOUNT_TYPE  = 2;


    private static final int ACTIVE = 1;
    private static final int INACTIVE = 0;


    public static int checkingType(){
        return CHECKING_ACCOUNT_TYPE;
    }

    public static final int savingType(){
        return SAVING_ACCOUNT_TYPE;
    }


    public static int activeStatus(){
        return ACTIVE;
    }

    public static int inactiveStatus(){
        return INACTIVE;
    }
}
