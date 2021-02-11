import java.util.*;
import java.util.stream.Collectors;

/**
 * Collection of various sort implementation for both lists and arrays
 *
 * <p>Sorts in ascending order by filling from either left or right
 */
public class SortPracticeForTest {

    public static void swap(Comparable[] data, int from, int to) {
        var tmp = data[from];
        data[from] = data[to];
        data[to] = tmp;
    }

    public static void swap(List<Comparable> data, int from, int to) {
        var tmp = data.get(from);
        data.set(from, data.get(to));
        data.set(to, tmp);
    }

    // Bubble sort list - ascending order
    public static void bubbleSortAscending(List<Comparable> data) {
        for (int count = 0; count < data.size(); ++count) {
            for (int ii = 0; ii < data.size() - 1; ++ii) {
                if (data.get(ii).compareTo(data.get(ii + 1)) > 0) {
                    swap(data, ii, ii + 1);
                }
            }
        }
    }

    // Bubble sort list - ascending order
    public static void bubbleSortAscending(Comparable[] data) {
        for (int count = 0; count < data.length; ++count) {
            for (int ii = 0; ii < data.length - 1; ++ii) {
                if (data[ii].compareTo(data[ii + 1]) > 0) {
                    swap(data, ii, ii + 1);
                }
            }
        }
    }

    // Selection sort: Ascending with sorted on left
    //  * Select the smallest entry in right unsorted section (since we are filling from left to
    // right)
    //  * Swap it with the first entry in the unsorted section (currently processed - pass)
    //  * Increase the size of sorted section to take the first entry from unsorted section
    public static void selectionSortAscendingLeft(List<Comparable> data) {
        for (int pass = 0; pass < data.size(); ++pass) {
            // Find the smallest entry from right remaining unsorted section
            // and move it to the top of unsorted section. When we advance the
            // outer loop, the top entry in unsorted section moves to last entry
            // in sorted section
            int minIndex = pass;
            for (int ii = pass + 1; ii < data.size(); ++ii) {
                if (data.get(ii).compareTo(data.get(minIndex)) < 0) {
                    minIndex = ii;
                }
            }

            swap(data, pass, minIndex);
        }
    }

    public static void selectionSortAscendingLeft(Comparable[] data) {
        for (int pass = 0; pass < data.length; ++pass) {
            int minIndex = pass;
            for (int ii = pass + 1; ii < data.length; ++ii) {
                if (data[ii].compareTo(data[minIndex]) < 0) {
                    minIndex = ii;
                }
            }

            swap(data, pass, minIndex);
        }
    }

    // Selection sort: Ascending with sorted on right
    //  * Select the highest entry in left unsorted section (since we are filling from right to
    // left)
    //  * Swap it with the last entry in the unsorted section (currently processed - pass)
    //  * Increase the size of sorted section to take the first entry from unsorted section
    public static void selectionSortAscendingRight(List<Comparable> data) {
        for (int pass = data.size() - 1; pass >= 0; --pass) {
            int maxIndex = pass;
            for (int ii = 0; ii < pass; ++ii) {
                if (data.get(ii).compareTo(data.get(maxIndex)) > 0) {
                    maxIndex = ii;
                }
            }

            swap(data, pass, maxIndex);
        }
    }

    public static void selectionSortAscendingRight(Comparable[] data) {
        for (int pass = data.length - 1; pass >= 0; --pass) {
            int maxIndex = pass;
            for (int ii = 0; ii < pass; ++ii) {
                if (data[ii].compareTo(data[maxIndex]) > 0) {
                    maxIndex = ii;
                }
            }

            swap(data, pass, maxIndex);
        }
    }

    // Insertion sort: Ascending with sorted on left
    //  * Divide the collection into sorted and unsorted
    //  * Consider the first element sorted and remaining unsorted
    //  * Pick first element from unsorted side and insert it at
    //  correct location in sorted side
    //  * Proceed and process all entries in unsorted side to
    //  eventually have everything moved to sorted side
    public static void insertionSortAscendingLeft(List<Comparable> data) {
        // Since we sort from left, we assume left most (first) is sorted
        for (int pass = 1; pass < data.size(); ++pass) {
            for (int ii = 0; ii < pass; ++ii) {
                // Find the appropriate location to insert the entry
                if (data.get(pass).compareTo(data.get(ii)) < 0) {
                    // Note: Removing first from location that is beyond the insertion
                    // point will not change the insertion point
                    data.add(ii, data.remove(pass));
                    break;
                }
            }
        }
    }

    // Insertion sort: Ascending with sorted on left
    public static void insertionSortAscendingLeft(Comparable[] data) {
        // Since we sort from left, we assume left most (first) is sorted
        for (int pass = 1; pass < data.length; ++pass) {
            for (int ii = 0; ii < pass; ++ii) {
                if (data[pass].compareTo(data[ii]) < 0) {
                    swap(data, ii, pass);
                }
            }
        }
    }

    // Insertion sort: Ascending with sorted on right
    public static void insertionSortAscendingRight(List<Comparable> data) {
        // Since we sort from right, we assume right most (first) is sorted
        for (int pass = data.size() - 2; pass >= 0; --pass) {
            for (int ii = data.size() - 1; ii > pass; --ii) {
                // Find the appropriate location to insert the entry
                if (data.get(pass).compareTo(data.get(ii)) > 0) {
                    // Note: Insert first because the entry we are going to remove
                    // is before the insertion index. By removing first, we will
                    // shift the insertion index by 1

                    // Insert after the smaller value to ensure we maintain ascending
                    data.add(ii + 1, data.get(pass));
                    data.remove(pass);
                    break;
                }
            }
        }
    }

    // Insertion sort: Ascending with sorted on right
    public static void insertionSortAscendingRight(Comparable[] data) {
        for (int pass = data.length - 2; pass >= 0; --pass) {
            for (int ii = data.length - 1; ii > pass; --ii) {
                if (data[pass].compareTo(data[ii]) > 0) {
                    swap(data, pass, ii);
                }
            }
        }
    }

    // Merge sort: Ascending
    public static void mergesort(List<Comparable> data, int first, int last) {
        int mid = first + (last - first) / 2;
        if (mid == first) {
            return;
        }

        mergesort(data, first, mid);
        mergesort(data, mid, last);

        for (int ii = first; ii < mid && mid < last; ++ii) {
            final Comparable self = data.get(ii);
            final Comparable other = data.get(mid);

            if (self.compareTo(other) > 0) {
                data.add(ii, data.remove(mid));
                ++mid;
            }
        }
    }

    // Merge sort: Ascending
    // Needs debugging (data is becoming empty)
    public static void mergesort(Comparable[] data, int first, int last) {
        int mid = first + (last - first) / 2;
        if (mid == first) {
            return;
        }

        System.out.println("first:" + first + " mid: " + mid + " last: " + last);
        Comparable[] left = Arrays.copyOfRange(data, first, mid + 1);
        Comparable[] right = Arrays.copyOfRange(data, mid, last);

        mergesort(left, first, mid);
        mergesort(right, mid, last);

        for (int ll = 0, rr = 0, idx = 0; ll < left.length && rr < right.length; ++idx) {
            int diff = left[ll].compareTo(right[rr]);
            if (diff <= 0) {
                data[idx] = left[ll];
                ++ll;
            }

            if (diff >= 0) {
                data[idx] = right[rr];
                ++rr;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Running sort tests...\n");

        Random rand = new Random();
        Integer[] arr = {};
        List<Comparable> coll = rand.ints(10, 0, 100).boxed().collect(Collectors.toList());

        try {
            // Bubble ascending sort - fill from right
            Collections.shuffle(coll, rand);
            arr = coll.toArray(new Integer[0]);
            bubbleSortAscending(arr);
            bubbleSortAscending(coll);
            assert coll.equals(Arrays.asList(arr));

            // Insertion ascending sort - fill from right
            Collections.shuffle(coll, rand);
            arr = coll.toArray(new Integer[0]);
            insertionSortAscendingLeft(arr);
            insertionSortAscendingLeft(coll);
            assert coll.equals(Arrays.asList(arr));

            // Insertion ascending sort - fill from right
            Collections.shuffle(coll, rand);
            arr = coll.toArray(new Integer[0]);
            insertionSortAscendingRight(arr);
            insertionSortAscendingRight(coll);
            assert coll.equals(Arrays.asList(arr));

            // Selection ascending sort - fill from left
            Collections.shuffle(coll, rand);
            arr = coll.toArray(new Integer[0]);
            selectionSortAscendingLeft(arr);
            selectionSortAscendingLeft(coll);
            assert coll.equals(Arrays.asList(arr));

            // Selection ascending sort - fill from right
            Collections.shuffle(coll, rand);
            arr = coll.toArray(new Integer[0]);
            selectionSortAscendingRight(arr);
            selectionSortAscendingRight(coll);
            assert coll.equals(Arrays.asList(arr));

            // Merge ascending sort
            Collections.shuffle(coll, rand);
            arr = coll.toArray(new Integer[0]);
            selectionSortAscendingRight(arr);
            // XXX: Fix mergesort of array
            // mergesort(arr, 0, arr.length);
            mergesort(coll, 0, coll.size());
            assert coll.equals(Arrays.asList(arr));
        } finally {
            System.out.println(Arrays.toString(arr));
            System.out.println(coll);
            System.out.println("");
        }
    }
}
