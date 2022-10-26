package me.imatveev.leetcode;

import me.imatveev.linkedlist.SortedLinkedList;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class PackageChecking {
    public static void main(String[] args) {

        final Package pack = Package.of(
                "A",
                Package.of(
                        "B",
                        Package.of("C")
                ),
                Package.of("D"),
                Package.of("E", Package.of("F", Package.of("G"))),
                Package.of("H", Package.of("I"), Package.of("D"))
        );

        System.out.println(getDependencyOrder(pack));
        System.out.println(hasCyclicDependencies(pack));
    }

    public static String getDependencyOrder(Package pack) {
        final Set<Package> packageSet = new LinkedHashSet<>();

        return getDependencyOrderStringRec(pack, packageSet);
    }

    public static boolean hasCyclicDependencies(Package pack) {
        if (pack.isVisited()) {
            return false;
        }

        pack.setVisited();

        for (Package innerPack : pack.packages) {
            if (hasCyclicDependencies(innerPack)) {
                return true;
            }
        }
        return false;
    }

    private static String getDependencyOrderStringRec(Package pack, Set<Package> acc) {
        for (Package innerPack : pack.packages) {
            getDependencyOrderStringRec(innerPack, acc);
        }

        acc.add(pack);

        return acc.stream()
                .map(it -> it.name)
                .collect(Collectors.joining(" -> "));
    }

    private static class Package {
        private final String name;
        private boolean visited;
        private final List<Package> packages;

        private Package(String name, List<Package> packages) {
            this.name = name;
            this.packages = packages;
            this.visited = false;
        }

        private Package(String name) {
            this.name = name;
            this.packages = Collections.emptyList();
            this.visited = false;
        }

        public static Package of(String name, Package... packages) {
            return new Package(name, List.of(packages));
        }

        public static Package of(String name) {
            return new Package(name);
        }

        public void setVisited() {
            this.visited = true;
        }

        public boolean isVisited() {
            return this.visited;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Package aPackage = (Package) o;
            return Objects.equals(name, aPackage.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }
}
