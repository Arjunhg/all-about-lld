package B_OPPS.Bc_Constructor;

class Singleton {
    private static Singleton instance;
    // Private constructor
    private Singleton() {}
    public static Singleton getInstance() {
      if (instance == null) {
        instance = new Singleton(); // <== 💥 object is created here
      }
      return instance;

      /*
       * This line is only executed once, and here's why:

          - instance is a private static variable of the class.

          - On the first call to getInstance(), instance is null, so it runs new Singleton().

          - That creates the object and assigns it to instance.

          - On subsequent calls, instance is no longer null, so new Singleton() is skipped, and the already-created object is returned.
       */
    }
  }
  
  public class Bc_D_Private {
    public static void main(String[] args) {
      Singleton s1 = Singleton.getInstance();
      Singleton s2 = Singleton.getInstance();

      // Singleton s3 = new Singleton(); ❌ Not allowed as the constructor is private

      System.out.println(s1==s2); // Output: true, as both references point to the same instance
    }
  }

  /*
   🧠 Quick Analogy:
      Imagine a company has one CEO. Anyone in the company asking for the CEO reference gets the same person. You can’t create a new CEO through new CEO() because the constructor is private.
   */