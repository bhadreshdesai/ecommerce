package bdd.demo.ecommerce.inventory;

import bdd.demo.ecommerce.base.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryService extends BaseService<Inventory, Long, InventoryRepository> {

   InventoryRepository getRepository() {
      return this.repository;
   }

   @Transactional(readOnly = true)
   public boolean isInStock(String skuCode) {
      return this.getRepository().findBySkuCode(skuCode).isPresent();
   }
}
