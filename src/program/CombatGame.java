// <-- Developed by Samitha Jayarathne -->
package program;

abstract class Gun {

    protected String name;
    protected int remainBullets;
    protected int maxBullets;
    protected int magSize;
    protected int remainBulletsInMag;

    protected abstract void fire();

    protected void reload(String soldier) {
        if (remainBullets > 0) {
            remainBulletsInMag = magSize;
            System.out.println(this.name + " reloaded by " + soldier + " - remaining bullets :" + remainBullets);
        } else {
            System.out.println(name + " is out of bullets! switch the gun!!!");
        }

    }

    Gun(String name, int remainBullets, int magSize) {
        this.name = name;
        this.remainBullets = remainBullets;
        this.maxBullets = remainBullets;
        this.magSize = magSize;
        this.remainBulletsInMag = magSize;
    }
}

class AK47 extends Gun {

    public AK47() {
        super("AK47", 300, 30);
    }

    @Override
    protected void fire() {
        if (remainBullets > 0) {
            if (remainBulletsInMag > 0) {
                remainBullets -= 2;
                remainBulletsInMag -= 2;
                System.out.println(name + " is firing... Remaining bullets in magazine: " + remainBulletsInMag);
            } else {
                System.out.println(name + " magazine is empty! hurry up.. reload the gun");
            }

        } else {
            System.out.println(name + " is out of bullets!");
        }
    }

}

class Pistol extends Gun {

    public Pistol() {
        super("Pistol", 18, 6);
    }

    @Override
    protected void fire() {
        if (remainBullets > 0) {
            if (remainBulletsInMag > 0) {
                remainBullets--;
                remainBulletsInMag--;
                System.out.println(name + " is firing... Remaining bullets in magazine: " + remainBulletsInMag);
            } else {
                System.out.println(name + " magazine is empty! hurry up.. reload the gun");
            }

        } else {
            System.out.println(name + " is out of bullets!");
        }
    }

}

class Sniper extends Gun {

    public Sniper() {
        super("Sniper", 10, 5);
    }

    @Override
    protected void fire() {
        if (remainBullets > 0) {
            if (remainBulletsInMag > 0) {
                remainBullets--;
                remainBulletsInMag--;
                System.out.println(name + " is firing... Remaining bullets in magazine: " + remainBulletsInMag);
            } else {
                System.out.println(name + " magazine is empty! hurry up.. reload the gun");
            }

        } else {
            System.out.println(name + " is out of bullets!");
        }
    }
}

class Soldier {

    String name;
    Gun[] gunsArray;
    Gun currentGun;
    int gunsIndex = 0;

    boolean isNotWarnedOutOfBullets = true;

    public Soldier(String name) {
        this.name = name;
        System.out.println("Soldier - " + name + " created!");
        this.gunsArray = new Gun[3];
    }

    public void pickGun(Gun gun) {
        for (Gun g : gunsArray) {
            if (g == gun) {
                System.out.println(name + " already picked " + gun.name);
                return;
            }
        }

        if (gunsIndex < 3) {
            gunsArray[gunsIndex] = gun;
            gunsIndex++;
            System.out.println(name + " picked " + gun.name);
            currentGun = gun;
        } else {
            System.out.println("A soldier can hold a maximum of three guns at a time.");
        }
    }

    public void switchGuns(Gun gun) {
        for (Gun g : gunsArray) {
            if (g == gun) {
                currentGun = gun;
                System.out.println(name + " switched to " + gun.name);
                return;
            }
        }
        System.out.println(name + " doesn't have " + gun.name + " to switch!");
    }

    public void dropGun(Gun gun) {
        for (int i = 0; i < gunsArray.length; i++) {
            if (gunsArray[i] == gun) {
                System.out.println(name + " dropped " + gun.name);
                gunsArray[i] = null;
                if (currentGun == gun) {
                    currentGun = null;
                }
                gunsIndex--;
                return;
            }
        }
        System.out.println(name + " doesn't have " + gun.name + " to drop.");
    }

    public void shoot() {
        if (currentGun != null) {
            if (currentGun.remainBullets > 0) {
                if (currentGun.remainBulletsInMag > 0) {
                    currentGun.fire();
                    isNotWarnedOutOfBullets = true;
                } else if (isNotWarnedOutOfBullets) {
                    System.out.println(this.name + "'s " + currentGun.name + " magazine is empty! Hurry up.. reload the gun");
                    isNotWarnedOutOfBullets = false;
                }
            } else {
                System.out.println(this.name + "'s " + currentGun.name + " is out of bullets! Switch the gun!!!");
            }
        } else {
            System.out.println(name + "... pick a gun to shoot!!!");
        }
    }

    public void reloadGun() {
        if (currentGun != null) {
            currentGun.reload(name);
            isNotWarnedOutOfBullets = true;
        } else {
            System.out.println(name + "... pick a gun to reload!!!");
        }
    }
}

public class CombatGame {

    public static void main(String[] args) {
      
        Soldier john = new Soldier("John");
        Soldier ghost = new Soldier("Ghost");

       
        Gun ak47 = new AK47();
        Gun pistol = new Pistol();
        Gun sniper = new Sniper();
        
        john.pickGun(ak47);
        john.pickGun(ak47);
        
        

//        // Soldier John picks a sniper
//        john.pickGun(sniper);
//
//        // Soldier John shoots 7 times (demonstrates firing and reload behavior)
//        System.out.println("\nJohn starts shooting with the sniper:");
//        for (int i = 0; i < 7; i++) {
//            john.shoot();  // John fires the sniper
//        }
//
//        // Reload the sniper
//        john.reloadGun();
//
//        // Soldier John shoots 6 more times after reloading
//        System.out.println("\nJohn shoots after reloading the sniper:");
//        for (int i = 0; i < 6; i++) {
//            john.shoot();  // John fires the sniper
//        }
//
//        // Reload the sniper again
//        john.reloadGun();
//
//        // Soldier John shoots 2 more times after reloading
//        System.out.println("\nJohn shoots after reloading the sniper again:");
//        for (int i = 0; i < 2; i++) {
//            john.shoot();  // John fires the sniper
//        }
//
//        // Soldier John switches to AK47
//        john.switchGuns(ak47);
//
//        // Soldier John shoots with AK47
//        System.out.println("\nJohn switches to AK47 and starts shooting:");
//        for (int i = 0; i < 4; i++) {
//            john.shoot();  // John fires AK47
//        }
//
//        // Soldier John reloads AK47
//        john.reloadGun();
//
//        // Soldier John shoots with AK47 after reload
//        System.out.println("\nJohn reloads AK47 and shoots:");
//        for (int i = 0; i < 5; i++) {
//            john.shoot();  // John fires AK47
//        }
//
//        // Soldier John switches to Pistol
//        john.switchGuns(pistol);
//
//        // Soldier John shoots with Pistol
//        System.out.println("\nJohn switches to Pistol and starts shooting:");
//        for (int i = 0; i < 6; i++) {
//            john.shoot();  // John fires Pistol
//        }
//
//        // Soldier John tries to shoot again but the pistol magazine is empty
//        john.shoot();  // This should trigger a reload warning
//
//        // Soldier John reloads the Pistol
//        john.reloadGun();
//
//        // Soldier John shoots with Pistol after reload
//        System.out.println("\nJohn reloads Pistol and shoots:");
//        john.shoot();  // John fires Pistol
//
//        // Soldier John drops the Sniper
//        john.dropGun(sniper);
//
//        // Soldier John tries to shoot with the Sniper (which he dropped)
//        System.out.println("\nJohn tries to shoot with the Sniper after dropping it:");
//        john.shoot();  // This should indicate that John needs to pick up a gun
//
//        // Soldier Ghost picks up a new gun and shoots
//        ghost.pickGun(ak47);
//        System.out.println("\nGhost picks up AK47 and starts shooting:");
//        for (int i = 0; i < 3; i++) {
//            ghost.shoot();  // Ghost fires AK47
//        }
//
//        // Soldier Ghost reloads the AK47
//        ghost.reloadGun();
//
//        // Soldier Ghost shoots after reloading
//        System.out.println("\nGhost reloads AK47 and shoots:");
//        ghost.shoot();  // Ghost fires AK47

    }
}
