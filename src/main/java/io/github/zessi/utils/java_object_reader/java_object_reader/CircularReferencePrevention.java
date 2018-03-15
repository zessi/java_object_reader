package io.github.zessi.utils.java_object_reader.java_object_reader;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * This class is used to prevent circular object references when attempting to get the String representation of an object. <br>
 * The class holds information about a set of objects that are currently being processed <br>
 * The class provides functionality to add or remove one or more objects from the set of objects. <br>
 * How it works.
 * <ul>
 * <li>
 * Create an instance of this type for each input object (let's call it input_object) that is supposed to have its String representation evaluated
 * </li>
 * <li>
 * If the input_object has internal objects that will have their String representation calculated recursively, then the following should happen
 * </li>
 * <li>
 * Just before an internal object gets processed for its String representation, it should be checked to see if it exists in the set of currentlyProcessing objects.
 * </li>
 * <li>
 * If the object exists, then that's a case of circular reference, and it should be handled
 * </li>
 * <li>
 * If the object doesn't exist then that object should be added to the set of currentlyProcessing objects. And then have its String representation evaluated.
 * </li>
 * <li>
 * Once an internal object finishes its String representation calculation, then it should be immediately removed from the set of currentlyProcessing objects.
 * </li>
 * <li>
 * The object is passed down recursively to each object that will have its data String represented
 * </li>
 * </ul>
 */
class CircularReferencePrevention {

    /**
     * The set of objects that are currently being processed.
     */
    private final Set<Object> currentlyProcessing = new HashSet<>();

    /**
     * Checks weather the input object exists within the set of currentlyProcessing objects or not.
     *
     * @param inputObject The object that should be checked if it exists in the set of currentlyProcessing objects or not.
     * @return true if the object exists, false otherwise.
     */
    synchronized boolean checkExists(Object inputObject) {
        boolean exists = false;
        for (Object element : this.currentlyProcessing) {
            if (element == inputObject) exists = true;
        }
        return exists;
    }

    /**
     * A method that is used to add a collection of objects to the set of currentlyProcessing objects.
     *
     * @param objects The collection that contains the objects that should be added.
     */
    synchronized void addAll(Collection objects) {
        this.currentlyProcessing.addAll(objects);
    }

    /**
     * A method that is used to add an object to the set of currentlyProcessing objects.
     *
     * @param o The object to be added.
     */
    synchronized void add(Object o) {
        this.currentlyProcessing.add(o);
    }

    /**
     * A method that is used to remove an object from the set of currentlyProcessing objects.
     *
     * @param o The object to be removed
     */
    synchronized void remove(Object o) {
        this.currentlyProcessing.remove(o);
    }

    /**
     * A method that is used to remove a collection of objects from the set of currentlyProcessing objects.
     *
     * @param objects The collection that contains the objects that should be removed
     */
    synchronized void removeAll(Collection objects) {
        this.currentlyProcessing.removeAll(objects);
    }

}
