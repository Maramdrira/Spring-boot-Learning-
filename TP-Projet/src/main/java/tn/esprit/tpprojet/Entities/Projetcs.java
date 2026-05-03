package tn.esprit.tpprojet.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.*;
public class Projetcs {


// =====================================================================================
//                    SPRING BOOT ENTITIES - COMPLETE REFERENCE
// =====================================================================================



    // ENUM EXAMPLE 1: Basic enum for project status
    public enum Status {
        PENDING,    // 0 if ORDINAL, "PENDING" if STRING
        IN_PROGRESS,// 1 if ORDINAL, "IN_PROGRESS" if STRING
        COMPLETED,  // 2 if ORDINAL, "COMPLETED" if STRING
        CANCELLED   // 3 if ORDINAL, "CANCELLED" if STRING
    }

    // ENUM EXAMPLE 2: Enum with custom values and methods
    public enum Domaine {
        WEB("Web Development", 101),
        MOBILE("Mobile Apps", 102),
        DATA("Data Science", 103),
        CLOUD("Cloud Computing", 104),
        AI("Artificial Intelligence", 105);

        private String description;
        private int code;

        // Enum constructor (always private)
        Domaine(String description, int code) {
            this.description = description;
            this.code = code;
        }

        public String getDescription() { return description; }
        public int getCode() { return code; }
    }


// =====================================================================================
// SECTION 2: MAIN ENTITY WITH ALL RELATIONSHIPS
// =====================================================================================


    @Entity
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @EqualsAndHashCode
    @FieldDefaults(level = AccessLevel.PRIVATE)  // Makes all fields private automatically
    @Table(name = "project_table")  // Custom table name (optional)
    public class Project {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id;

        // ============================= BASIC FIELDS =============================
        String title;
        String description;
        double budget;
        int duration;

        @Enumerated(EnumType.STRING)
        Status status;

        @Enumerated(EnumType.ORDINAL)
        Domaine domaine;

        @Temporal(TemporalType.DATE)  // DATE only (no time)
                Date startDate;

        @Temporal(TemporalType.TIMESTAMP)  // Date + Time
        Date createdAt;

    }



// =====================================================================================
// SECTION 4: CASCADE TYPES - COMPLETE REFERENCE
// =====================================================================================
    /*
     * CASCADE TYPES EXPLANATION:
     *
     * CascadeType.PERSIST    - When you save parent, child is also saved
     * CascadeType.MERGE      - When you update parent, child is also updated
     * CascadeType.REMOVE     - When you delete parent, child is also deleted
     * CascadeType.REFRESH    - When you refresh parent, child is also refreshed
     * CascadeType.DETACH     - When you detach parent, child is also detached
     * CascadeType.ALL        - All of the above combined
     *
     */



// =====================================================================================
//ONE-TO-ONE X → Y (Unidirectional - X knows Y, Y doesn't know X) code ken fel x
// =====================================================================================

    // CLASS X (owns the relationship)
    @Entity
    public class X {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @OneToOne(cascade = CascadeType.ALL)  // OPTIONAL: cascade
        // FK in X table pointing to Y
        private Y y;
    }


// =====================================================================================
// ONE-TO-ONE X - Y (Bidirectional - both know each other)
// =====================================================================================

    // CLASS X (OWNING side - has FK)
   @Entity
   public class X1 {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;

       @OneToOne(cascade = CascadeType.ALL)
       // FK in X table
       private Y y;
   }

    // CLASS Y (INVERSE side - uses mappedBy)
    @Entity
    public class Y {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;

        @OneToOne(mappedBy = "y")  // "y" = field name in X class
        private X x;  // Reference back to X
    }


// =====================================================================================
//ONE-TO-MANY X → Y (Unidirectional - X knows Y's, Y doesn't know X) tkteb ken fel x
// =====================================================================================


// CLASS X (ONE side - knows many Y's)
    @Entity
    public class X2 {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @OneToMany(cascade = CascadeType.ALL)  // NO mappedBy!
        // FK added in Y table
        private Set<Y> yList = new HashSet<>();
    }

// =====================================================================================
// ONE-TO-MANY X - Y (Bidirectional - both know each other)
// =====================================================================================


// CLASS X (ONE side - INVERSE side uses mappedBy)
public class X3 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "xu", cascade = CascadeType.ALL)  // mappedBy = field in Y
    private Set<Y> yList = new HashSet<>();

}

    // CLASS Y (MANY side - OWNING side has FK)
    public class Y2 {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne  // OWNING side!
        // FK in Y table
        private X xu;
    }

// =====================================================================================
//MANY-TO-ONE X → Y (Unidirectional - X knows Y, Y doesn't know X's) lkitba ken fel x
// =====================================================================================

    // CLASS X (MANY side - knows its ONE Y)
    @Entity
    public class X4 {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne  // MANY X's point to ONE Y
         // FK in X table
        private Y y;
    }

// =====================================================================================
//  MANY-TO-ONE X - Y (Bidirectional - both know each other)
// =====================================================================================

    // CLASS X (MANY side - OWNING side has FK)
    @Entity
    public class X5 {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
         // FK in X table
        private Y5 y5;
    }

    // CLASS Y (ONE side - INVERSE side)
    @Entity
    public class Y5 {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @OneToMany(mappedBy = "y")  // mappedBy = field in X
        private Set<X> xList = new HashSet<>();
    }

// =====================================================================================
// MANY-TO-MANY X → Y (Unidirectional - X knows Y's, Y doesn't know X's) ken fel x w famma table jdida
// =====================================================================================

    // CLASS X (OWNING side - creates join table)
    @Entity
    public class X6 {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
        private Set<Y> yList = new HashSet<>();
    }

// =====================================================================================
//MANY-TO-MANY X - Y (Bidirectional - both know each other) table jdida
// =====================================================================================

    // CLASS X (OWNING side - defines @JoinTable)
    @Entity
    public class X7 {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
        private Set<Y> yList = new HashSet<>();
    }

    // CLASS Y (INVERSE side - uses mappedBy)
    @Entity
    public class Y7 {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToMany(mappedBy = "yList")  // "yList" = field name in X class
        private Set<X> xList = new HashSet<>();
    }


//    1:1 = FK in one table (whoever has @JoinColumn)
//    1:N = FK in MANY table (Y table)
//    N:1 = FK in MANY table (X table)
//    N:N = NEW TABLE always!
//    mappedBy = "I don't own this, look in other class"









    /*
     * =====================================================================================
     * COMPLETE RELATIONSHIP REFERENCE TABLE
     * =====================================================================================
     * Relationship | Direction    | Where is FK? | New Table? | mappedBy on? | Cascade on? |
     * =============|==============|==============|============|==============|=============|
     * 1:1          | X→Y (uni)    | X table      | No         | Not used     | Either side |
     * 1:1          | X-Y (bi)     | X table      | No         | Y class      | Either side |
     * 1:N          | X→Y (uni)    | Y table      | No         | Not used     | X side      |
     * 1:N          | X-Y (bi)     | Y table      | No         | X class (One) | X side      |
     * N:1          | X→Y (uni)    | X table      | No         | Not used     | X side      |
     * N:1          | X-Y (bi)     | X table      | No         | Y class (One) | X side      |
     * N:N          | X→Y (uni)    | Neither      | YES!       | Not used     | Either side |
     * N:N          | X-Y (bi)     | Neither      | YES!       | Y class (inv) | X side (own)
     * =====================================================================================
     *
     * LEGEND:
     * - X→Y (uni)     = Unidirectional (X knows Y, Y doesn't know X)
     * - X-Y (bi)      = Bidirectional (both know each other)
     * - FK            = Foreign Key
     * - mappedBy      = Inverse side (does NOT own the relationship)
     * =====================================================================================
     */

    /*
     * =====================================================================================
     * QUICK REFERENCE BY RELATIONSHIP TYPE
     * =====================================================================================
     *
     * ONE-TO-ONE (1:1)
     * =================
     * Unidirectional:        Bidirectional:
     * class X {              class X {
     *   @OneToOne              @OneToOne
     *   Y y;                   Y y;
     * }                      }
     *
     *                        class Y {
     * class Y {                @OneToOne(mappedBy = "y")
     *   // nothing             X x;
     * }                      }
     *
     * ONE-TO-MANY (1:N)
     * =================
     * Unidirectional:        Bidirectional:
     * class X {              class X {
     *   @OneToMany             @OneToMany(mappedBy = "x")
     *    Set<Y> yList;         Set<Y> yList;
     * }                           }
     *                        class Y {
     * class Y {                @ManyToOne
     *   // nothing             @JoinColumn
     * }                        X x;
     *                        }
     *
     * MANY-TO-ONE (N:1)
     * =================
     * Unidirectional:        Bidirectional:
     * class X {              class X {
     *   @ManyToOne             @ManyToOne
     *   Y y;                   Y y;
     * }                      }
     *
     * class Y {              class Y {
     *   // nothing             @OneToMany(mappedBy = "y")
     * }                        Set<X> xList;
     *                        }
     *
     * MANY-TO-MANY (N:N)
     * =================
     * Unidirectional:        Bidirectional:
     * class X {              class X {
     *   @ManyToMany            @ManyToMany
     *   Set<Y> yList;          Set<Y> yList;
     * }                      }
     *
     * class Y {              class Y {
     *   // nothing             @ManyToMany(mappedBy = "yList")
     * }                        Set<X> xList;
     *                        }
     * =====================================================================================
     */

// =====================================================================================
// END OF ENTITY REFERENCE - GOOD LUCK ON YOUR TEST! 🍀
// =====================================================================================




}
