# LRUCACHE
This Java program implements a Least Recently Used (LRU) Cache using a Doubly Linked List (DLL) and a HashMap. The combination of these data structures allows efficient data retrieval and management, ensuring constant-time operations for both access (get) and insertion (put).

The DLL maintains the order of cache elements based on their usage, with the most recently accessed elements moved to the front. When the cache reaches its capacity, the least recently used (LRU) element is automatically removed. The HashMap provides quick lookup of cache entries, enabling O(1) access time.

This implementation ensures optimal cache performance, making it suitable for use cases requiring fast data retrieval with limited memory constraints
