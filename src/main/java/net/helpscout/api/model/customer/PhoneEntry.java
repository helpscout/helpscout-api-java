package net.helpscout.api.model.customer;

public class PhoneEntry extends CustomerEntry {

    public static enum Location {
        Home("home"),
        Work("work"),
        Mobile("mobile"),
        Fax("fax"),
        Pager("pager"),
        Other("other");

        private final String label;

        private Location(String label) {
            this.label = label;
        }

        public String getLabel() {
            return this.label;
        }

        public static Location findByLabel(String label) {
            for (Location item : Location.values()) {
                if (item.getLabel().equalsIgnoreCase(label)) {
                    return item;
                }
            }
            return null;
        }
    }
}