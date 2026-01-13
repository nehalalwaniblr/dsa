package system_desig_use_cases.lsm;

class Entry {
    final String key;
    final String value;
    final long seq;
    final boolean tombstone;

    Entry(String key, String value, long seq, boolean tombstone) {
        this.key = key;
        this.value = value;
        this.seq = seq;
        this.tombstone = tombstone;
    }
}
