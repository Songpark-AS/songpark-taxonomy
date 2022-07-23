(ns songpark.taxonomy.fx
  (:require [clojure.spec.alpha :as spec]))


(spec/def :fx/base (spec/keys :opt [:fx/type]))

(spec/def :fx/type #{:fx/gate
                     :fx/reverb
                     :fx/amplify
                     :fx/compressor
                     :fx/echo
                     :fx/equalizer})

;; panorama
(spec/def :fx/pan number?)
;; gain
(spec/def :fx/gain number?)

;; gate
(spec/def :fx.gate/threshold number?)
(spec/def :fx.gate/attack number?)
(spec/def :fx.gate/release number?)

(spec/def :fx/gate (spec/merge
                    :fx/base
                    (spec/keys :req [:fx.gate/threshold
                                     :fx.gate/attack
                                     :fx.gate/release])))

;; reverb
(spec/def :fx.reverb/mix number?)
(spec/def :fx.reverb/damp number?)
(spec/def :fx.reverb/room-size number?)

(spec/def :fx/reverb (spec/merge
                      :fx/base
                      (spec/keys :req [:fx.reverb/mix
                                       :fx.reverb/damp
                                       :fx.reverb/room-size])))


;; amplify
(spec/def :fx.amplify/drive number?)
(spec/def :fx.amplify/tone number?)

(spec/def :fx/amplify (spec/merge
                       :fx/base
                       (spec/keys :req [:fx.amplify/drive
                                        :fx.amplify/tone])))

;; equalizer
(spec/def :fx.equalizer/switch boolean?)
(spec/def :fx.equalizer/low number?)
(spec/def :fx.equalizer/medium-low number?)
(spec/def :fx.equalizer/medium-high number?)
(spec/def :fx.equalizer/high number?)

(spec/def :fx/equalizer (spec/merge
                         :fx/base
                         (spec/keys :req [:fx.equalizer/low
                                          :fx.equalizer/medium-low
                                          :fx.equalizer/medium-high
                                          :fx.equalizer/high])))

;; echo
(spec/def :fx.echo/delay-time number?)
(spec/def :fx.echo/level number?)

(spec/def :fx/echo (spec/merge
                    :fx/base
                    (spec/keys :req [:fx.echo/delay-time
                                     :fx.echo/level])))

;; compressor
(spec/def :fx.compressor/threshold number?)
(spec/def :fx.compressor/ratio number?)
(spec/def :fx.compressor/attack number?)
(spec/def :fx.compressor/release number?)

(spec/def :fx/compressor (spec/merge
                          :fx/base
                          (spec/keys :req [:fx.compressor/threshold
                                           :fx.compressor/ratio
                                           :fx.compressor/attack
                                           :fx.compressor/release])))

;; presets

;; this spec is a bit special. originally a spec that and'ed
;; :fx/base, a spec/or with all the fx and a function that checked
;; that it was the correct corresponding data with the type was used
;; it worked well with manual testing, but when used with reitit
;; it failed. when tested with the data that was sent in over transit
;; and back to the browser the specs checked out. something is off deep
;; within the machinery.
;; this spec solves all the problems we actually want to check against,
;; but is slightly less informative when data is returned
;; originally :fx/base had :fx/type as :req, but here it is always mandatory
;; anyway, since it's always checked against
(spec/def :fx/fx
  (fn [{fx-type :fx/type :as data}]
    (if (nil? fx-type)
      false
      (spec/valid? fx-type data))))

(spec/def :fx/fxs (spec/coll-of :fx/fx))

(spec/def :fx.preset/name string?)
(spec/def :fx.preset/id number?)
(spec/def :fx/preset (spec/keys :req [:fx.preset/id
                                      :fx.preset/name
                                      :fx/fxs]))
(spec/def :fx/presets (spec/coll-of :fx/preset))

(spec/def :fx.preset/save (spec/keys :req [:fx.preset/name
                                           :fx/fxs]))
(spec/def :fx.preset/update (spec/keys :req [:fx.preset/id
                                             :fx.preset/name
                                             :fx/fxs]))
(spec/def :fx.preset/delete (spec/keys :req [:fx.preset/id]))


;; (spec/explain :fx.preset/save {:fx.preset/name "string1",
;;                                :fx/fxs [{:fx/type :fx/echo,
;;                                          :fx.echo/delay-time 0,
;;                                          :fx.echo/level 0}]})

;; (spec/explain :fx.preset/save {:fx.preset/name "string1",
;;                                :fx/fxs [{:fx/type :fx/gate,
;;                                          :fx.gate/threshold 100
;;                                          :fx.gate/release 100
;;                                          :fx.gate/attack 100}]})

;; (spec/explain :fx.preset/update {:fx.preset/name "string1",
;;                                  :fx.preset/id 1
;;                                  :fx/fxs [{:fx/type :fx/echo,
;;                                            :fx.echo/delay-time 0,
;;                                            :fx.echo/level 0}]})

;; (spec/explain :fx.preset/delete {:fx.preset/id 1})


;; (spec/explain :fx/fx {:fx/type :fx/echo,
;;                       :fx.echo/delay-time 0,
;;                       :fx.echo/level 0})
