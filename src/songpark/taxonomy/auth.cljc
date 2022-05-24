(ns songpark.taxonomy.auth
  (:require [clojure.spec.alpha :as spec]
            [clojure.string :as str]
            [songpark.taxonomy.profile]
            [songpark.taxonomy.util :refer [tick?]]))

(spec/def :auth.user/id number?)
(spec/def :auth.user/email (spec/and string?
                                     #(str/includes? % "@")))
(spec/def :auth.user/password (spec/and string?
                                        #(>= (count %) 8)))
(spec/def :auth.user/repeat-password :auth.user/password)
(spec/def :auth.user/new-password :auth.user/password)
(spec/def :auth.user/verified-email? boolean?)
(spec/def :auth.user/token uuid?)
(spec/def :auth.user/token-at tick?)


(spec/def :auth/user (spec/keys :req [:auth.user/id
                                      :auth.user/email
                                      :auth.user/verified-email?]
                                :opt [:auth.user/token
                                      :auth.user/token-at
                                      :profile/name
                                      :profile/bio
                                      :profile/location
                                      :profile/pronoun]))

(spec/def :auth/login (spec/keys :req [:auth.user/email
                                       :auth.user/password]))

(spec/def :auth/verify-email (spec/keys :req [:auth.user/email]))

(spec/def :auth/signup (spec/keys :req [:auth.user/email
                                        :auth.user/password
                                        :auth.user/repeat-password
                                        :profile/name]))

(spec/def :auth/forgotten-password (spec/keys :req [:auth.user/email]))

(spec/def :auth/reset-password (spec/keys :req [:auth.user/token
                                                :auth.user/password
                                                :auth.user/new-password
                                                :auth.user/repeat-password]))
