package bdd.demo.ecommerce.inventory;

import bdd.demo.ecommerce.base.BaseController;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = Constants.APIPATH_INVENTORY)
@Slf4j
@Tag(name= Constants.INVENTORY_TAG, description = Constants.INVENTORY_API_DESC)
public class InventoryController extends BaseController<Inventory, Long, InventoryRepository> implements InventoryApi {

   InventoryService getService() {
      return (InventoryService) this.service;
   }

   @Override
   public boolean isInStock(String skuCode) {
      return this.getService().isInStock(skuCode);
   }
}
