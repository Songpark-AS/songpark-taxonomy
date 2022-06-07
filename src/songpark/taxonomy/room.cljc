(ns songpark.taxonomy.room
  (:require [clojure.spec.alpha :as spec]
            [clojure.string :as str]
            [songpark.taxonomy.auth]))

(spec/def :room/id number?)
(spec/def :room/owner :auth.user/id)
(spec/def :room/name (spec/and string?
                               #(not (str/blank? %))))

(spec/def :room/save (spec/keys :req [:room/name]))
(spec/def :room/update (spec/keys :req [:room/id
                                        :room/name]))

(spec/def :room/room (spec/keys :req [:room/id
                                      :room/name]
                                :opt [:room/owner]))
(spec/def :room/rooms (spec/coll-of :room/room))
