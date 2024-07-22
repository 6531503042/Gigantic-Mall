import axios from "axios";

const {
      USER_API_SERVICE_URL = "http://localhost:8100/Admin/users",
      CATEGORY_API_SERVICE_URL = "http://localhost:8100/category",
      BRAND_API_SERVICE_URL = "http://localhost:8100/Admin/brand",
      PRODUCT_API_SERVICE_URL = "http://localhost:8100/Admin/product",
      ORDER_API_SERVICE_URL = "http://localhost:8100/Admin/order",
      CUSTOMER_API_SERVICE_URL = "http://localhost:8100/Admin/customers"

} = process.env