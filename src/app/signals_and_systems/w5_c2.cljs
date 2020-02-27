(ns app.signals-and-systems.w5_c2
  (:require [app.view-utils.tools :refer [freehand-note-content default-notes-view]]
            [app.utils :as u]))

(defn _int [l h rest & [ds]] (str "int_(" l ")^(" h ") " rest ds))
(def om "omega")

(defn view []
  [freehand-note-content
   [
"## Poles and System Stability
`H(s)` for 2nd order systems. Roots or poles had 3 possibilities:
- Distinct
- Complex Conjugate
- Identical (repeated) poles

Assume `H(s)` has n distinct poles:"
    :form "H(s) = sum_(i=1)^(n) (A_i)/(s-p_i)"
    "For BiBo (Bounded input Bounded output) stability"
    :form (str (_int "-omega" om "|h(t)|dt") "< oo") 
    :form "int_(-omega)^(omega) |sum_(i=1)^(n) (A_i)e^(p_i*t)u(t)|dt < oo"
    "## Triangle inequality"
    :form "|x+y| <= |x| + |y|"
    :form "sum_(i=1)^(n) (A_i) int_(0)^(omega) |e^(p_i*t)u(t)|dt < oo"
    "So in order to be BiBo stable all terms must satisfy"
    :form " int_(0)^(omega) |e^(p_i*t)|dt < oo"
    :form "let p_i = d_i+jw_i"
    
    "## Rational Transfer Functions"
    :form "H(s) = (N(s))/(D(s))"
"N(S) - Polynomial in s, roots are zeros, order m (degree of the highest power of s)  
D(s) - Poles, order h
#### 3 cases
- m < n: strictly proper rational fraction **only one you can use partial fractions on**
- m = n: Proper rational function
- m > n: Improper rational function"
    :form "H(s) = (5s^2+3s+7)/(10s^2+14s+13)"
    "Make it proper by dividing out so that the s^2 are multiplied by 1"
    :form "H(s) = (N(s))/(D(s)) = (D(s) + N(s) - D(s))/(D(s))"
    "Skipping many steps"
    ;:form "H(s) = "
    "## Configurations of multiple Systems
    We know Y(s) = H(s)x(s)
    Series results in"
    :form "F(s) = T_1(s)T_2(s)X(s)"
    "Parallel results in"
    :form "F(s) = (T_1(s)+T_2(s))X(s)"]])


(u/register-note "SS1 Class 5 week 2" 
                 :ss_w5_c2
                 (constantly [default-notes-view "SS1 Class 5 week 2" view]))
