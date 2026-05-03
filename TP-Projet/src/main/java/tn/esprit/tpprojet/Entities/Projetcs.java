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
// COMPLETE RELATIONSHIPS REFERENCE - BASED ON YOUR COURSE
// =====================================================================================
// In your course, they DO NOT use @JoinColumn or @JoinTable explicitly!
// Spring Data JPA handles it automatically!
// =====================================================================================

// =====================================================================================
// 1. ONE-TO-ONE UNIDIRECTIONNEL (X → Y)
// =====================================================================================
// Slide: Page 10-12
// FK in X table (X knows Y, Y doesn't know X)

    @Entity
    public class X1 {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;

        @OneToOne(cascade = CascadeType.ALL)
        private Y1 y;
    }

    @Entity
    public class Y1 {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String description;
        // NO reference back to X!
    }

// =====================================================================================
// 2. ONE-TO-ONE BIDIRECTIONNEL (X ↔ Y)
// =====================================================================================
// Slide: Page 14-16
// FK in X table (Parent), mappedBy on Y (Child)
// "C'est le fils qui contient l'attribut mappedBy"

    @Entity
    public class X2 {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;

        @OneToOne(cascade = CascadeType.ALL)
        private Y2 y;  // Parent side - NO mappedBy
    }

    @Entity
    public class Y2 {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String description;

        @OneToOne(mappedBy = "y")  // Child side - HAS mappedBy
        private X2 x;
    }

// =====================================================================================
// 3. ONE-TO-MANY UNIDIRECTIONNEL (X → Y)
// =====================================================================================
// Slide: Page 18-20
// ⚠️ Creates a NEW JOIN TABLE in database!

    @Entity
    public class X3 {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;

        @OneToMany(cascade = CascadeType.ALL)  // NO mappedBy
        private Set<Y3> yList = new HashSet<>();  // Creates join table!
    }

    @Entity
    public class Y3 {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String description;
        // NO reference back to X!
    }

// =====================================================================================
// 4. ONE-TO-MANY BIDIRECTIONNEL = MANY-TO-ONE BIDIRECTIONNEL (X ↔ Y)
// =====================================================================================
// Slide: Page 27-29 (Many-to-One Bidirectionnelle)
// FK in Y table (Many side), mappedBy on X (One side)

    @Entity
    public class X4 {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;

        @OneToMany(mappedBy = "x", cascade = CascadeType.ALL)  // mappedBy = field in Y
        private Set<Y4> yList = new HashSet<>();
    }

    @Entity
    public class Y4 {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String description;

        @ManyToOne  // OWNING side!
        private X4 x;  // FK in Y table
    }

// =====================================================================================
// 5. MANY-TO-ONE UNIDIRECTIONNEL (X → Y)
// =====================================================================================
// Slide: Page 23-25
// FK in X table (Many side)

    @Entity
    public class X5 {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;

        @ManyToOne(cascade = CascadeType.ALL)  // FK in X table
        private Y5 y;
    }

    @Entity
    public class Y5 {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String description;
        // NO reference back to X!
    }

// =====================================================================================
// 6. MANY-TO-ONE BIDIRECTIONNEL (X ↔ Y)
// =====================================================================================
// Slide: Page 27-29
// FK in X table (Many side), mappedBy on Y (One side)

    @Entity
    public class X6 {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;

        @ManyToOne
        private Y6 y;  // FK in X table
    }

    @Entity
    public class Y6 {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String description;

        @OneToMany(mappedBy = "y")  // mappedBy = field in X
        private Set<X6> xList = new HashSet<>();
    }

// =====================================================================================
// 7. MANY-TO-MANY UNIDIRECTIONNEL (X → Y)
// =====================================================================================
// Slide: Page 31-33
// ⚠️ ALWAYS creates a NEW JOIN TABLE!

    @Entity
    public class X7 {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;

        @ManyToMany(cascade = CascadeType.ALL)  // Creates join table
        private Set<Y7> yList = new HashSet<>();
    }

    @Entity
    public class Y7 {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String description;
        // NO reference back to X!
    }

// =====================================================================================
// 8. MANY-TO-MANY BIDIRECTIONNEL (X ↔ Y)
// =====================================================================================
// Slide: Page 35-37
// ⚠️ ALWAYS creates a NEW JOIN TABLE!

    @Entity
    public class X8 {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;

        @ManyToMany(cascade = CascadeType.ALL)  // Owning side - creates join table
        private Set<Y8> yList = new HashSet<>();
    }

    @Entity
    public class Y8 {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String description;

        @ManyToMany(mappedBy = "yList")  // Inverse side - HAS mappedBy
        private Set<X8> xList = new HashSet<>();
    }

//    1:1 = FK in one table (whoever has @JoinColumn)
//    1:N = FK in MANY table (Y table)
//    N:1 = FK in MANY table (X table)
//    N:N = NEW TABLE always!
//    mappedBy = "I don't own this, look in other class"









    /*
     * =====================================================================================
     * COMPLETE RELATIONSHIP REFERENCE TABLE
/*
 * =====================================================================================
 * RELATIONSHIP REFERENCE TABLE (Based on Course Slides)
 * =====================================================================================
 *
 * RULE: mappedBy goes on CHILD (Fils) entity!
 *       Foreign Key goes in PARENT table!
 *
/*
 * =====================================================================================
 * COMPLETE RELATIONSHIP REFERENCE - WITH NEW TABLE CASES
 * =====================================================================================
 *
 * ⚠️ NEW TABLE IS CREATED IN 2 CASES:
 *    1. Many-to-Many (N:N) - ALWAYS creates a join table
 *    2. One-to-Many Unidirectional (1:N uni) - JPA creates a join table!
 *
 * =====================================================================================
 * Relationship | Direction    | Where is FK? | New Table? | mappedBy on? | Cascade on? | @JoinColumn?
 * =============|==============|==============|============|==============|=============|=============
 * 1:1          | X→Y (uni)    | X table      | ❌ No      | Not used     | Either side | Not needed
 * 1:1          | X-Y (bi)     | X table      | ❌ No      | Y (Child)    | X (Parent)  | Not needed
 * ---------------------------------------------------------------------------------------------------
 * 1:N          | X→Y (uni)    | JOIN TABLE!  | ✅ YES!    | Not used     | X side      | Not needed
 * 1:N          | X-Y (bi)     | Y table      | ❌ No      | X (One side) | X side      | Not needed
 * ---------------------------------------------------------------------------------------------------
 * N:1          | X→Y (uni)    | X table      | ❌ No      | Not used     | X side      | Not needed
 * N:1          | X-Y (bi)     | X table      | ❌ No      | Y (One side) | X side      | Not needed
 * ---------------------------------------------------------------------------------------------------
 * N:N          | X→Y (uni)    | JOIN TABLE!  | ✅ YES!    | Not used     | Either side | Not needed
 * N:N          | X-Y (bi)     | JOIN TABLE!  | ✅ YES!    | Y (Child)    | X (Parent)  | Not needed
 * =====================================================================================
 *
 * EXPLANATION:
 * - 1:N Unidirectional: JPA creates a join table because there's no @JoinColumn on the Many side
 * - 1:N Bidirectional: FK in the Many side table (Y table) - NO join table
 * - N:N: ALWAYS creates a join table (both uni and bidirectional)
 *
 * IMPORTANT: In your course, they DON'T use @JoinColumn explicitly!
 *            Spring Data JPA handles it automatically!
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
