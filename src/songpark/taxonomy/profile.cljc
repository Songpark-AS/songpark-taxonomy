(ns songpark.taxonomy.profile
  (:require [clojure.spec.alpha :as spec]))

(spec/def :profile/id number?)
(spec/def :profile/name string?)
(spec/def :profile/bio string?)
(spec/def :profile/pronoun string?)
(spec/def :profile/location string?)
(spec/def :profile/image-url string?)
(spec/def :profile/profile (spec/keys :req [:profile/name
                                            :profile/bio
                                            :profile/location
                                            :profile/image-url]
                                      :opt [:profile/id
                                            :profile.pronoun/id]))

(spec/def :profile.pronoun/id number?)
(spec/def :profile.pronoun/name string?)
(spec/def :profile/pronoun (spec/keys :req [:profile.pronoun/id
                                            :profile.pronoun/name]))
