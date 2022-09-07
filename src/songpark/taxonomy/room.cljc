(ns songpark.taxonomy.room
  (:require [clojure.spec.alpha :as spec]
            [clojure.string :as str]
            [songpark.taxonomy.auth]
            [songpark.taxonomy.profile]
            [songpark.taxonomy.util :refer [tick?]]))

(spec/def :room/id number?)
(spec/def :room/owner :auth.user/id)
(spec/def :room/name (spec/and string?
                               #(not (str/blank? %))))
(spec/def :room/name-normalized (spec/and string?
                                          #(not (str/blank? %))))
(spec/def :room/last-jammed tick?)
(spec/def :room/last-jammers (spec/coll-of string?))

(spec/def :room/save (spec/keys :req [:room/name]))
(spec/def :room/update (spec/keys :req [:room/id
                                        :room/name]))

(spec/def :room/room (spec/keys :req [:room/id
                                      :room/name
                                      :room/name-normalized]
                                :opt [:room/owner
                                      :room/last-jammed
                                      :room/last-jammers]))
(spec/def :room/rooms (spec/coll-of :room/room))

(spec/def :room/jammer (spec/keys :req [:auth.user/id
                                        :profile/name
                                        :profile/position]
                                  :opt [:profile/image-url]))
(spec/def :room/jammers (spec/coll-of :room/jammer))
(spec/def :room.jammer/id :auth.user/id)

(spec/def :room/session (spec/keys :req [:room/id]))
(spec/def :room.jam/host (spec/keys :req [:room/id]))
(spec/def :room.jam/hosted (spec/keys :req [:room/id
                                            :room/owner
                                            :room/name
                                            :room/name-normalized]))
(spec/def :room.jam/knock (spec/keys :req [:room/name]))
(spec/def :room.jam/knocked (spec/keys :req [:room/id
                                             :room/name]))
(spec/def :room.jam/accept (spec/keys :req [:room/id
                                            :room/jammer]))
(spec/def :room.jam/decline (spec/keys :req [:room/id
                                             :room/jammer]))
(spec/def :room.jam/leave (spec/keys :req [:room/id]))
(spec/def :room.jam/remove (spec/keys :req [:room/id
                                            :room.jammer/id]))
(spec/def :room.jam/close (spec/keys :req [:room/id]))
