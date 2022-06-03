(ns songpark.taxonomy.profile
  (:require [clojure.string :as str]
            [clojure.spec.alpha :as spec]))

(spec/def :profile/id number?)
(spec/def :profile/name (spec/and string?
                                  #(not (str/blank? %))))
(spec/def :profile/pronoun string?)
(spec/def :profile/position string?)
(spec/def :profile/image-url string?)
(spec/def :profile.image/base64 string?)
(spec/def :profile.image/type string?)
(spec/def :profile/profile (spec/keys :req [:profile/name
                                            :profile/position]
                                      :opt [:profile/id
                                            :profile/image-url
                                            :profile.pronoun/id]))

(spec/def :profile/save (spec/keys :req [:profile/name
                                         :profile/position
                                         :profile.pronoun/id]
                                   :opt [:profile.image/base64
                                         :profile.image/type]))

(spec/def :profile.pronoun/id number?)
(spec/def :profile.pronoun/name string?)
(spec/def :profile/pronoun (spec/keys :req [:profile.pronoun/id
                                            :profile.pronoun/name]))
(spec/def :profile/pronouns (spec/coll-of :profile/pronoun))
