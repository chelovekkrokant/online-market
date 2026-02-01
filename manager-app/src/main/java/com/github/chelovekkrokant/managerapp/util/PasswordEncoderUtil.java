package com.github.chelovekkrokant.managerapp.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Scanner;

/**
 * Utility class for password encoding operations using BCrypt.
 * Provides methods for encoding plain text passwords and verifying passwords.
 * Can be run as a standalone CLI tool for password operations.
 */
public class PasswordEncoderUtil {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private PasswordEncoderUtil() {
        // Private constructor to prevent instantiation
    }

    /**
     * Encodes a plain text password using BCrypt.
     *
     * @param plainPassword the plain text password to encode
     * @return the BCrypt encoded password
     */
    public static String encode(String plainPassword) {
        return passwordEncoder.encode(plainPassword);
    }

    /**
     * Verifies if a plain text password matches an encoded password.
     *
     * @param plainPassword the plain text password
     * @param encodedPassword the BCrypt encoded password
     * @return true if the passwords match, false otherwise
     */
    public static boolean matches(String plainPassword, String encodedPassword) {
        return passwordEncoder.matches(plainPassword, encodedPassword);
    }

    /**
     * Gets the PasswordEncoder instance.
     *
     * @return the BCryptPasswordEncoder instance
     */
    public static PasswordEncoder getEncoder() {
        return passwordEncoder;
    }

    /**
     * CLI entry point for password encoding operations.
     * Run this main method in your IDE to interactively encode/verify passwords.
     *
     * @param args command line arguments (not used in interactive mode)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("╔═══════════════════════════════════════╗");
        System.out.println("║   Password Encoder Utility (BCrypt)   ║");
        System.out.println("╚═══════════════════════════════════════╝");
        System.out.println();

        while (true) {
            printMenu();
            System.out.print("Select option: ");

            String choice = scanner.nextLine().trim();
            System.out.println();

            switch (choice) {
                case "1":
                    encodePasswordInteractive(scanner);
                    break;

                case "2":
                    verifyPasswordInteractive(scanner);
                    break;

                case "3":
                    encodeMultiplePasswords(scanner);
                    break;

                case "0":
                case "exit":
                case "quit":
                    System.out.println("Goodbye! 👋");
                    scanner.close();
                    return;

                default:
                    System.out.println("❌ Invalid option. Please try again.\n");
            }
        }
    }

    private static void printMenu() {
        System.out.println("─────────────────────────────────────");
        System.out.println("What would you like to do?");
        System.out.println("─────────────────────────────────────");
        System.out.println("  1. Encode a password");
        System.out.println("  2. Verify a password");
        System.out.println("  3. Encode multiple passwords");
        System.out.println("  0. Exit");
        System.out.println("─────────────────────────────────────");
    }

    private static void encodePasswordInteractive(Scanner scanner) {
        System.out.print("Enter password to encode: ");
        String password = scanner.nextLine();

        if (password.isEmpty()) {
            System.out.println("❌ Password cannot be empty!\n");
            return;
        }

        String encoded = encode(password);

        System.out.println("\n✅ Password encoded successfully!");
        System.out.println("┌─────────────────────────────────────");
        System.out.println("│ Plain text:  " + password);
        System.out.println("│ BCrypt hash: " + encoded);
        System.out.println("└─────────────────────────────────────\n");
    }

    private static void verifyPasswordInteractive(Scanner scanner) {
        System.out.print("Enter plain text password: ");
        String plainPassword = scanner.nextLine();

        System.out.print("Enter BCrypt hash: ");
        String encodedPassword = scanner.nextLine();

        if (plainPassword.isEmpty() || encodedPassword.isEmpty()) {
            System.out.println("❌ Both fields are required!\n");
            return;
        }

        boolean matches = matches(plainPassword, encodedPassword);

        System.out.println();
        if (matches) {
            System.out.println("✅ SUCCESS: Password matches the hash!");
        } else {
            System.out.println("❌ FAILED: Password does NOT match the hash!");
        }
        System.out.println();
    }

    private static void encodeMultiplePasswords(Scanner scanner) {
        System.out.println("Enter passwords (one per line). Type 'done' when finished:");
        System.out.println();

        int count = 0;
        while (true) {
            System.out.print("Password " + (count + 1) + ": ");
            String password = scanner.nextLine().trim();

            if (password.equalsIgnoreCase("done")) {
                break;
            }

            if (password.isEmpty()) {
                System.out.println("⚠️  Skipping empty password...");
                continue;
            }

            String encoded = encode(password);
            System.out.println("   → " + encoded);
            count++;
        }

        System.out.println("\n✅ Encoded " + count + " password(s)\n");
    }
}