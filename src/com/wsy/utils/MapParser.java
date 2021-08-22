package com.wsy.utils;

import java.io.*;
import java.util.*;

/**
 * @author WSY
 */
public class MapParser {


    public static void main(String[] args) {
        try {
//            File currentDirFile = new File(".");
//            String helper = currentDirFile.getAbsolutePath();
//            String currentDir = helper.substring(0, helper.length() - currentDirFile.getCanonicalPath().length());
//            System.out.println("helper = " + helper + " , currentDir = " + currentDir);

            File file = new File("file");
            String absolutePath = file.getAbsolutePath();
            System.out.println("absolutePath = " + absolutePath);

            travelDir(file);

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    private static void travelDir(File dir) throws IOException {
        if (dir == null || !dir.isDirectory()) {
            return;
        }

        File[] files = dir.listFiles();
        if (files == null) {
            return;
        }

        for (File file : files) {
            if (file.isDirectory()) {
                travelDir(file);
            } else {
                parseFile(file);
            }
        }

    }

    private static void parseFile(File file) throws IOException {
        if (file == null) {
            return;
        }

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = null;

        boolean canRead = false;

        List<Section> noneZeroList = new ArrayList<>();
        List<Section> zeroList = new ArrayList<>();

//        HashSet<String> suffixSet = new HashSet();

        while ((line = reader.readLine()) != null) {
//            System.out.println(line);

            if (!canRead) {
                System.out.println(line);

                if (line.startsWith("Address")) {
                    canRead = true;
                }

                continue;
            }

            if (line.startsWith("---")) {
                continue;
            }

            if (line.startsWith("total")) {
                System.err.println(line);
                continue;
            }

            // 解析 常规 数据行
//            System.out.println(line);

            String[] arr = line.split(" +", 7);
            if (arr.length < 7) {
                return;
            }
//            System.out.println("arr = " + Arrays.toString(arr));


            Section section = new Section(arr[0],
                    Integer.parseInt(arr[1]), Integer.parseInt(arr[2]), Integer.parseInt(arr[3]), Integer.parseInt(arr[4]),
                    arr[5], arr[6]);

            if (section.PSS == 0) {
                zeroList.add(section);
            } else {
                noneZeroList.add(section);
            }

//            if (section.Mapping.contains("anon")){
//                System.out.println(line);
//            }

//            String[] split = section.Mapping.split("\\.");
////            System.out.println(Arrays.toString(split));
//
//            if (split.length > 1) {
//                String suffix = split[split.length - 1];
////                System.out.println("file = " + section.Mapping + " , arr = " + Arrays.toString(split));
//                if (!section.Mapping.contains("anon")) {
//                    System.out.println("file = " + section.Mapping + " , suffix = " + suffix);
//                    suffixSet.add(suffix);
//                }
//            }

        }

//        System.out.println("suffixSet = " + suffixSet.toString());

        System.out.println("zeroList size = " + zeroList.size());
        System.out.println("noneZeroList size = " + noneZeroList.size());


        processZeroList(zeroList);

        System.out.println();

        processNonZeroList(noneZeroList);
    }

    private static void processZeroList(List<Section> list) {
        if (list == null || list.size() == 0) {
            return;
        }

        System.out.println("========================= PSS = 0  START ========================= ");

//        for (Section section : list) {
//            System.out.println(section);
//        }

        calculateNormalZero(list);


        System.out.println("========================= PSS = 0  END ========================= ");

    }


    private static void calculateNormalZero(List<Section> list) {
        // 动态的
        int anonSize = 0;
        int dalvikSize = 0;

        // 文件型
        int odexSize = 0;
        int soSize = 0;
        int apkSize = 0;
        int jarSize = 0;
        int vdexSize = 0;
        int artSize = 0;
        int oatSize = 0;
        // 未知 文件
        int otherFileSize = 0;

        // 未知
        int unknownSize = 0;

        for (Section section : list) {
            if (section.Mapping.contains("anon")) {
                anonSize += section.Kbytes;
            } else if (section.Mapping.contains("dalvik")) {
                dalvikSize += section.Kbytes;
            } else {
                String[] split = section.Mapping.split("\\.");

                // 有后缀名的
                if (split.length > 1) {

                    String suffix = split[split.length - 1];
                    switch (suffix) {
                        case "odex":
                            odexSize += section.Kbytes;
                            break;
                        case "so":
                            soSize += section.Kbytes;
                            break;
                        case "apk":
                            apkSize += section.Kbytes;
                            break;
                        case "jar":
                            jarSize += section.Kbytes;
                            break;
                        case "vdex":
                            vdexSize += section.Kbytes;
                            break;
                        case "art":
                            artSize += section.Kbytes;
                            break;
                        case "oat":
                            oatSize += section.Kbytes;
                            break;
                        default:
                            otherFileSize += section.Kbytes;

                            if (section.Kbytes > 1000) {
                                System.out.println("large unknown file = " + section);
                            }
                            break;
                    }

                } else {
                    // 无后缀名的
                    unknownSize += section.Kbytes;

                    if (section.Kbytes > 1000) {
                        System.out.println("large unknown = " + section);
                    }
                }

            }

        }

        int fileSize = odexSize + soSize + apkSize + jarSize + vdexSize + artSize + oatSize + otherFileSize;

        int totalKbytes = anonSize + dalvikSize + fileSize + unknownSize;

        StringBuilder sb = new StringBuilder();
        sb.append("\ntotalKbytes = ").append(totalKbytes)
                .append("k [ anon = ").append(anonSize)
                .append("k, dalvik = ").append(dalvikSize)
                .append("k,  fileSize = ").append(fileSize)
                .append("k ( odex = ").append(odexSize)
                .append(", vdex = ").append(vdexSize)
                .append(", so = ").append(soSize)
                .append(", apk = ").append(apkSize)
                .append(", jar = ").append(jarSize)
                .append(", art = ").append(artSize)
                .append(", oat = ").append(oatSize)
                .append(", otherFile = ").append(otherFileSize)
                .append(" ), unknown = ").append(unknownSize)
                .append("k ]");

        System.out.println(sb.toString());

    }


    /**
     * suffixSet = [art, ttc, oat, ttf, mmap2, pcm (deleted), vdex, dat, db (deleted), jar, odex, so, idx, apk]
     * <p>
     * 文件 后缀名：
     * <p>
     * （1）代码
     * odex
     * so
     * apk
     * jar
     * vdex
     * art
     * oat
     * <p>
     * （2）字体
     * ttc
     * ttf
     * <p>
     * （3）未知
     * dat
     * idx
     */
    private static void processNonZeroList(List<Section> list) {
        if (list == null || list.size() == 0) {
            return;
        }

        System.out.println("========================= PSS != 0  START ========================= ");

        calculateNormalNonZero(list);

        System.out.println("========================= PSS != 0  END ========================= ");

    }

    private static void calculateNormalNonZero(List<Section> list) {
        // 动态的
        int anonSize = 0;
        int dalvikSize = 0;

        HashMap<String, List<Section>> anonMap = new HashMap<>();
        HashMap<String, List<Section>> dalvikMap = new HashMap<>();
        HashMap<String, List<Section>> fileMap = new HashMap<>();

        // 文件型
        int odexSize = 0;
        int soSize = 0;
        int apkSize = 0;
        int jarSize = 0;
        int vdexSize = 0;
        int artSize = 0;
        int oatSize = 0;
        // 未知 文件
        int otherFileSize = 0;

        // 未知
        int unknownSize = 0;


        for (Section section : list) {
            if (section.Mapping.contains("anon")) {
                anonSize += section.PSS;

//                System.out.println(section);

                List<Section> sections = anonMap.get(section.Mapping);
                if (sections == null) {
                    sections = new ArrayList<>();
                    anonMap.put(section.Mapping, sections);
                }
                sections.add(section);

            } else if (section.Mapping.contains("dalvik")) {
                dalvikSize += section.PSS;

//                System.out.println(section);

                List<Section> sections = dalvikMap.get(section.Mapping);

                if (sections == null) {
                    sections = new ArrayList<>();
                    dalvikMap.put(section.Mapping, sections);
                }

                sections.add(section);

            } else {
                String[] split = section.Mapping.split("\\.");

                // 有后缀名的
                if (split.length > 1) {

                    String suffix = split[split.length - 1];
                    switch (suffix) {
                        case "odex":
                            odexSize += section.PSS;
                            break;
                        case "so":
                            soSize += section.PSS;
                            break;
                        case "apk":
                            apkSize += section.PSS;
                            break;
                        case "jar":
                            jarSize += section.PSS;
                            break;
                        case "vdex":
                            vdexSize += section.PSS;
                            break;
                        case "art":
                            artSize += section.PSS;
                            break;
                        case "oat":
                            oatSize += section.PSS;
                            break;
                        default:
                            otherFileSize += section.PSS;

                            if (section.PSS > 100) {
                                System.out.println("large unknown file = " + section);
                            }
                            break;
                    }

                    // 特殊处理下
                    String mapping = section.Mapping;
                    if (mapping.startsWith("Plugin_") && mapping.contains(".")) {
                        mapping = mapping.substring(0, mapping.lastIndexOf((".")));
                    }

                    List<Section> sections = fileMap.get(mapping);
                    if (sections == null) {
                        sections = new ArrayList<>();
                        fileMap.put(mapping, sections);
                    }

                    sections.add(section);
                } else {
                    // 无后缀名的
                    unknownSize += section.PSS;

                    if (section.PSS > 100) {
                        System.out.println("large unknown = " + section);
                    }
                }

            }

        }

        // pss 计算
        int fileSize = odexSize + soSize + apkSize + jarSize + vdexSize + artSize + oatSize + otherFileSize;
        int totalPss = anonSize + dalvikSize + fileSize + unknownSize;

        StringBuilder sb = new StringBuilder();
        sb.append("\n======== totalPss = ").append(totalPss).append("k");


        sb.append("\n\nanon = ").append(anonSize).append("k ,anonMap size = ").append(anonMap.size());
        HashMap<String, Integer> anonSizeMap = calculateSectionMap(anonMap, sb);


        sb.append("\n\ndalvik = ").append(dalvikSize).append("k ,dalvikMap size = ").append(dalvikMap.size());
        HashMap<String, Integer> dalvikSizeMap = calculateSectionMap(dalvikMap, sb);


        sb.append("\n\nfile = ").append(fileSize).append("k ,fileMap size = ").append(fileMap.size());
        sb.append("\n\todex = ").append(odexSize)
                .append("k\n\tvdex = ").append(vdexSize)
                .append("k\n\tso = ").append(soSize)
                .append("k\n\tapk = ").append(apkSize)
                .append("k\n\tjar = ").append(jarSize)
                .append("k\n\tart = ").append(artSize)
                .append("k\n\toat = ").append(oatSize)
                .append("k\n\totherFile = ").append(otherFileSize).append("k\n");
        HashMap<String, Integer> fileSizeMap = calculateSectionMap(fileMap, sb);


        sb.append("\n\nunknown = ").append(unknownSize).append("k");

        System.out.println(sb.toString());
    }

    /**
     * 计算 size
     */
    private static HashMap<String, Integer> calculateSectionMap(HashMap<String, List<Section>> srcMap, StringBuilder sb) {
        HashMap<String, Integer> srcSizeMap = new HashMap<>();

        int size = 0;

        int totalSize = 0;

        for (Map.Entry<String, List<Section>> entry : srcMap.entrySet()) {
            size = 0;

            List<Section> list = entry.getValue();
            for (Section section : list) {
                size += section.PSS;
                totalSize += section.PSS;
            }

            srcSizeMap.put(entry.getKey(), size);
        }

        System.out.println("\ncalculateSectionMap map size = " + totalSize + "k");

        for (Map.Entry<String, Integer> entry : srcSizeMap.entrySet()) {
            sb.append("\n\t").append(entry.getKey()).append(" = ").append(entry.getValue()).append("k");
        }

        return srcSizeMap;
    }


    static class Section {

        public String Address;
        public int Kbytes;
        public int PSS;
        public int Dirty;
        public int Swap;
        public String Mode;
        public String Mapping;

        public Section(String address, int kbytes, int PSS, int dirty, int swap, String mode, String mapping) {
            Address = address;
            Kbytes = kbytes;
            this.PSS = PSS;
            Dirty = dirty;
            Swap = swap;
            Mode = mode;
            Mapping = mapping;
        }

        @Override
        public String toString() {
            return "Section{" +
                    "Address= '" + Address + '\'' +
                    ", Kbytes= " + Kbytes +
                    ", PSS= " + PSS +
                    ", Dirty= " + Dirty +
                    ", Swap= " + Swap +
                    ", Mode= " + Mode +
                    ", Mapping= '" + Mapping + '\'' +
                    '}';
        }
    }

}
