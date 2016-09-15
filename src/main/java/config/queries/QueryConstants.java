package config.queries;

public class QueryConstants {
    public static final String QERY_ALL_ITEMS_FROM_REPOSITORY = "select item, store FROM Item item inner join item.store as store";
    public static final String QERY_ITEM_BY_ID_FROM_REPOSITORY = "SELECT item FROM Item item where item.id = ";
}
