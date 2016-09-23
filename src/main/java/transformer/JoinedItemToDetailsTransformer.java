package transformer;

import domain.Details;
import domain.DetailsInterface;
import domain.JoinedItemInterface;

public class JoinedItemToDetailsTransformer {
    public static DetailsInterface transform(JoinedItemInterface joinedItem){
        DetailsInterface details = new Details();
        details.setAmount(joinedItem.getStore().getAmount());
        details.setDescription(joinedItem.getItem().getDescription());
        details.setName(joinedItem.getItem().getName());
        details.setPrice(joinedItem.getItem().getPrice());
        
        return details;
    }
}
