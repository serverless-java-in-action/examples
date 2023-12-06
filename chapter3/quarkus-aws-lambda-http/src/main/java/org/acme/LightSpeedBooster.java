// package org.acme;

// import java.util.List;

// import io.quarkus.hibernate.orm.panache.PanacheEntity;
// import jakarta.persistence.Cacheable;
// import jakarta.persistence.Entity;

// @Entity
// @Cacheable
// public class LightSpeedBooster extends PanacheEntity {

//     public String name;
//     public int quantity;
//     public int credit; // Galactic Standard Credit
//     public boolean isWarpDrive; // If a lightspeed rocket booster supports a warp drive

//     public static List<LightSpeedBooster> findWarpDrive(){
//         return list("isWarpDrive", true);
//     }
// }
