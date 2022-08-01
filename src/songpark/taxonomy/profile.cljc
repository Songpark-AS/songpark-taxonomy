(ns songpark.taxonomy.profile
  (:require [clojure.string :as str]
            [clojure.spec.alpha :as spec]))

(spec/def :profile/id number?)
(spec/def :profile/name (spec/and string?
                                  #(not (str/blank? %))))
(spec/def :profile/position string?)
(spec/def :profile/image-url string?)
(spec/def :profile.image/base64 string?)
(spec/def :profile.image/type string?)
(spec/def :profile/profile (spec/keys :req [:profile/name
                                            :profile/position]
                                      :opt [:profile/id
                                            :profile/image-url]))

(spec/def :profile/save (spec/keys :req [:profile/name
                                         :profile/position]
                                   :opt [:profile.image/base64
                                         :profile.image/type]))
