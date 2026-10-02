### Requirement Collection
#### Products
* Support snacks and beverages.
* Each product has a unique productId.
* Each slot contains only one type of product.
* A slot can contain multiple units.
* Products can have different prices.
#### models.Product selection
* User selects a product using the slot number.
* The machine checks whether the selected product is in stock before accepting payment.
#### Cash payment
* Support different coin/note denominations: ₹1, ₹2, ₹5, ₹10, ₹20, ₹50, ₹100.
* User can insert multiple denominations.
* If inserted amount is greater than the price, return change.
* If the machine cannot provide the required change, cancel the transaction and return the entire inserted amount.
* User can cancel and receive the entire inserted amount back.
#### Card payment
* Support card payment.
* Payment must succeed before the product is dispensed.
#### inventory.Inventory
* Maintain product quantity for every slot.
* Restocking should be supported.
* models.Product quantity decreases after successful dispensing.

#### Transaction
* A transaction should be atomic from the user's perspective:
Payment → models.Product + Change
* Failed transactions should not leave the machine in an inconsistent state.

#### Operations
* Provide an interface for:
  - Restocking products
  - Collecting money
  
#### Exceptional cases
* Out-of-stock product
* Insufficient payment
* Insufficient change
* Payment failure
* User cancellation

### Core classes/entities
* VendingMachine
* models.Product
* inventory.Inventory
* inventory.InventoryItem
* ProductDispenser
* Denomination
* Payment
