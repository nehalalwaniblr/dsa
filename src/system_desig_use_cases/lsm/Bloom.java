package system_desig_use_cases.lsm;

import java.util.BitSet;

class Bloom {
    /*
    * Sure — let’s break **BitSet** down simply and then connect it back to why we used it (Bloom filters, LSMs, etc.).

---

# 🧩 What is a BitSet?

A **BitSet** is a data structure that stores values as **bits (0/1)** instead of full integers or booleans.

In Java:

```java
BitSet bits = new BitSet();
```

Internally it looks like:

```
index:  0 1 2 3 4 5 6 7 ...
value:  0 1 0 0 1 0 0 1 ...
```

Each position is either:

* `0` → false / not set
* `1` → true / set

Because each entry is only **1 bit**, it is extremely memory-efficient.

---

# 🧠 Why use BitSet instead of boolean[] ?

Consider:

```java
boolean[] arr = new boolean[1000];
```

Even though they’re logical booleans, the JVM stores each as **1 byte or more**, not 1 bit.

So:

```
1000 booleans ≈ 1000 bytes
```

But with BitSet:

```java
BitSet b = new BitSet(1000);
```

Now memory cost:

```
1000 bits ≈ 125 bytes
```

👉 **8x smaller** (or more).

---

# ⚙️ How BitSet works conceptually

Think of it like a compact flag array:

```java
bits.set(3);
bits.set(7);
```

Now:

```
index: 0 1 2 3 4 5 6 7
value: 0 0 0 1 0 0 0 1
```

Check if something is set:

```java
boolean exists = bits.get(3); // true
boolean exists2 = bits.get(5); // false
```

Clear a bit:

```java
bits.clear(3);
```

Now index 3 goes back to 0.

---

# 🧪 Example: Using BitSet as a tiny membership table

```java
BitSet b = new BitSet();

b.set(10);
b.set(25);

System.out.println(b.get(10)); // true
System.out.println(b.get(11)); // false
```

---

# 🌸 Why BitSet is perfect for BLOOM FILTERS

A Bloom filter needs:

1️⃣ A big array of bits
2️⃣ Fast hashing to choose positions

When we store a key:

```java
bits.set(h1(key));
bits.set(h2(key));
```

When we query:

```java
return bits.get(h1(key)) && bits.get(h2(key));
```

So BitSet gives us:

✔ compact memory
✔ fast random access
✔ built-in optimization from JVM

Bloom filter uses millions of bits cheaply — impossible with normal booleans.

---

# 🧠 Where BitSet shows up in real systems

* Bloom filters (RocksDB, Cassandra, Kafka, Redis Bloom module)
* Bitmap indexes in databases
* Compression schemes
* Permissions/feature flags
* Graph algorithms (visited sets)

---

# 🎯 One-sentence summary

> **BitSet is a memory-efficient array of bits (0/1), ideal when you only need yes/no flags at large scale — like Bloom filters.**

---

If you'd like, I can also explain:

📌 how BitSet is stored internally (long[] array)
📌 how hashing functions map to bit positions
📌 time/space complexity compared to HashSet
📌 why Bloom filters use multiple bit positions

Just say the word!

    * */
    private final BitSet bits;
    private final int size;

    Bloom(int size) {
        this.size = size;
        this.bits = new BitSet(size);
    }

    private int h1(String k) {
        return Math.abs(k.hashCode()) % size;
    }

    private int h2(String k) {
        return Math.abs(k.hashCode() * 31) % size;
    }

    public void add(String key) {
        bits.set(h1(key));
        bits.set(h2(key));
    }

    public boolean mightContain(String key) {
        return bits.get(h1(key)) && bits.get(h2(key));
    }
}
