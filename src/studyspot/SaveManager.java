// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Armaan
//
// LLM Statement:
// See README.md

package studyspot;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * Saves and restores the complete StudySpot model using Java serialization.
 * 
 * @author Armaan
 * @version 2026.11.16
 */
public class SaveManager {
    /**
     * Writes a model to disk.
     *
     * @param model
     *            model to save
     * @param file
     *            destination file
     * @throws IOException
     *             if saving fails
     */
    public void save(StudySpot model, Path file) throws IOException {
        if (model == null || file == null) {
            throw new IllegalArgumentException("Model and file are required.");
        }
        Path absolute = file.toAbsolutePath();
        Path parent = absolute.getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }
        Path temporary = Files.createTempFile(parent, "studyspot-", ".tmp");
        try {
            try (ObjectOutputStream output = new ObjectOutputStream(Files
                .newOutputStream(temporary))) {
                output.writeObject(model);
            }
            try {
                Files.move(temporary, absolute,
                    StandardCopyOption.REPLACE_EXISTING,
                    StandardCopyOption.ATOMIC_MOVE);
            }
            catch (java.nio.file.AtomicMoveNotSupportedException e) {
                Files.move(temporary, absolute,
                    StandardCopyOption.REPLACE_EXISTING);
            }
        }
        finally {
            Files.deleteIfExists(temporary);
        }
    }


    /**
     * Reads a saved model.
     *
     * @param file
     *            save file
     * @return restored model
     * @throws IOException
     *             if reading fails
     */
    public StudySpot load(Path file) throws IOException {
        if (file == null) {
            throw new IllegalArgumentException("File is required.");
        }
        try (ObjectInputStream input = new ObjectInputStream(Files
            .newInputStream(file))) {
            Object restored = input.readObject();
            if (!(restored instanceof StudySpot)) {
                throw new IOException(
                    "Save file does not contain StudySpot data.");
            }
            return (StudySpot)restored;
        }
        catch (ClassNotFoundException e) {
            throw new IOException("Could not read saved StudySpot data.", e);
        }
    }
}
