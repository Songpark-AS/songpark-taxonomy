(ns songpark.taxonomy.pairing
  (:require [clojure.spec.alpha :as spec]
            [songpark.taxonomy.auth]
            [songpark.taxonomy.teleporter]))


(spec/def :pairing/pair (spec/keys :req [:teleporter/serial]))
(spec/def :pairing/unpair (spec/keys :req [:teleporter/id]))
(spec/def :pairing/paired (spec/keys :req [:teleporter/id
                                           :teleporter/nickname]))
(spec/def :pairing/pairs (spec/coll-of :pairing/paired))

(spec/def :pairing.teleporter/paired (spec/keys :req [:teleporter/id
                                                      :auth.user/id]))
