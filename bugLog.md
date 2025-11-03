## Bug 1
Brief description: Computer constructor fails to allocate memory correctly. It is always 16 GB instead of the expected input.
Failed unit test: testComputerConstructor()

## Bug 2
Brief description: ResaleShop constructor's default Computer price is value 0 instead of 1000.
Failed unit test: testResaleShopConstructor()

## Bug 3
Brief description: The setOS() method fails to update the operating system of the computer as it should be. The OS remains "None" instead of getting updated to the new version given.
Failed unit test: testSetOS()

## Bug 4
Brief description:  The toString() operation prints incorrect memory and price values (prints 16 GB memory and 0 price instead of expected 8 GB and 1500).
Failed unit test: testToString()

## Bug 5
Brief description: The resaleshop constructor fails to create a blank inventory. It always adds a computer to the shop.
Failed unit test: testResaleShopConstructor()

## Bug 6
Brief description: The resale shop constructor fails to allocate price for the computer. It's always 0 instead of expected 1000.
Failed unit test: testResaleShopConstructor()

## Bug 7
Brief description: The buy() method always constructs a new fixed computer instead of using the one given, so there is never any duplication detection and no exception is ever thrown.
Failed unit test: testBuy()

## Bug 8
Brief description: The sell() operation does not throw an exception when attempting to sell a computer that is out of stock.
Failed unit test: testSellNonExistingComputer()

## Bug 9
Brief description: An IndexOutOfBoundException is expected to be thrown from the method.
Failed unit test: testPrintInventory()

## Bug 10
Brief description: The refurbish() price logic assigns unrealistic or incorrect prices to year ranges (e.g., computers between 2012–2017 at price 550 even if newer).
Failed unit test: testRefurbish()

