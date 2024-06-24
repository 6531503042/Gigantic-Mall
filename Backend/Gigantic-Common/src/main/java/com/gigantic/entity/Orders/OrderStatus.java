package com.gigantic.entity.Orders;

public enum OrderStatus {

    NEW {
        @Override
        public String defaultDescription() {
            return "Order was placed by the customer";
        }
    },

    PROCESSING {
        public String defaultDescription() {
            return "Order is being processed";
        }
    },

    PACKAGE {
        public String defaultDescription() {
            return "Order is in package";
        }
    },

    PICKED {
        public String defaultDescription() {
            return "Order was picked";
        }
    },

    SHIPPED {
        public String defaultDescription() {
            return "Order was shipped";
        }
    },

    DELIVERED {
        public String defaultDescription() {
            return "Order was delivered";
        }
    },

    PAID {
        public String defaultDescription() {
            return "Order was paid";
        }
    },

    RETURN_REQUESTED {
        public String defaultDescription() {
            return "Customer send request to return the order";
        }
    },

    RETURN {
        public String defaultDescription() {
            return "Order was returned";
        }
    },

    REFUND {
        public String defaultDescription() {
            return "Order was refunded";
        }
    },

    CANCELLED {
        public String defaultDescription() {
            return "Order was cancelled";
        }
    };

    public abstract String defaultDescription();
}
