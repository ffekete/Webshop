package config.queries;

public class QueryConstants {
    public static final String QERY_ALL_ITEMS_FROM_REPOSITORY = "select item, store FROM Item item inner join item.store as store";
    public static final String QERY_ITEM_BY_ID_FROM_REPOSITORY = "SELECT item FROM Item item where item.id = ";
    public static final String QERY_STORE_BY_ITEM_ID_FROM_REPOSITORY = "SELECT store FROM Store store where store.id = :id";
    public static final String UPDATE_ITEM_AMOUNT_IN_STORE = "UPDATE Store store set store.amount= :newAmount where store.id= :id";
}
