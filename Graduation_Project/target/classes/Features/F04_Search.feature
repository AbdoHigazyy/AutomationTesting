@Smoke
Feature: Search functionality

  Scenario Outline: User could search using product name
    Given user navigates to the homepage
    When user searches for "<product_name>"
    Then user get products that contains the word "<product_name>" successfully
    And each result should contain the search word "<product_name>"

    Examples:
      | product_name |
      | Book         |
      | Laptop       |
      | Nike         |

  Scenario Outline: User could search for product using SKU
    Given user navigates to the homepage
    When user searches for SKU "<sku>"
    Then click on the product in search result
    And SKU on product page should match the searched SKU "<sku>"
    Examples:
      | sku          |
      | SCI_FAITH    |
      | APPLE_CAM    |
      | AP_MBP_13    |