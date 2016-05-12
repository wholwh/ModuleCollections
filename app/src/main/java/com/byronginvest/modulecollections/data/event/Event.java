package com.byronginvest.modulecollections.data.event;

/**
 * Created by Gosha on 2016-02-15.
 */
public class Event {
    public static class ItemOnClickEvent {
        private String key;
        private String Value;

        public ItemOnClickEvent(String key, String value) {
            this.key = key;
            Value = value;
        }

        public String getValue() {
            return Value;
        }

        public void setValue(String value) {
            Value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }
}
