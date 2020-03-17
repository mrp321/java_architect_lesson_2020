package cn.sitedev.course;

public class CourseTest {
    public static void main(String[] args) {
        Course java = new Course("Java 架构");
        Course javaBase = new Course("Java 入门");
        Course design = new Course("Java 设计模式精讲");
        Course ai = new Course("人工智能");

        CourseAggregate aggregate = new CourseAggregateImpl();
        aggregate.add(java);
        aggregate.add(javaBase);
        aggregate.add(design);
        aggregate.add(ai);

        System.out.println("============课程列表============");
        printCourses(aggregate);

        aggregate.remove(ai);

        System.out.println("============执行删除操作之后的课程列表============");
        printCourses(aggregate);

    }

    private static void printCourses(CourseAggregate aggregate) {
        Iterator<Course> iterator = aggregate.iterator();
        while (iterator.hasNext()) {
            Course course = iterator.next();
            System.out.println("《" + course.getName() + "》");
        }
    }
}
