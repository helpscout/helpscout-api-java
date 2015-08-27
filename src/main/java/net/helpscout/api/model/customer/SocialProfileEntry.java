package net.helpscout.api.model.customer;

public class SocialProfileEntry extends CustomerEntry {

    public static enum Type {
        Twitter("twitter"),
        Facebook("facebook"),
        LinkedIn("linkedin"),
        AboutMe("aboutme"),
        Google("google"),
        GooglePlus("googleplus"),
        TungleMe("tungleme"),
        Quora("quora"),
        Foursquare("foursquare"),
        YouTube("youtube"),
        Flickr("flickr"),
        Other("other");

        private final String label;

        private Type(String label) {
            this.label = label;
        }

        public String getLabel() {
            return this.label;
        }

        public static Type findByLabel(String label) {
            for (Type item : Type.values()) {
                if (item.getLabel().equalsIgnoreCase(label)) {
                    return item;
                }
            }
            return Type.Other;
        }
    }
}