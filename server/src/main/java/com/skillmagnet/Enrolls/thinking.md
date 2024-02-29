Update **Course Progress** on enrollment object

Need to know how many lessons available in course

```
course.getLessons()
```

Need to know what lessons user have completed, for example maybe

```
user.getCompletedLessons()

returns list of id's of completed lessons
[1,2,3,4]

```

This could also be another join table that is similar to enrollment, if we wanted to abstract it out of user

Idk maybe something like a table called completed?

so like user -> completed -> lesson

```
completed.getCompletedLessons(User u)
```

- **Do we want to know how much progress they have in the lesson?**

  IE: minwatched/totalLessonMin

  ```json
  LessonId: Progress
         1: 100
         2: 50
         3: 0
  ```

  **150 / 300 = 50% course progress**

  So enrollment would do a more precise calculation

- **Or if they have just completed a lesson**
  IE: lessonid=1: 0 - not finished, 1 - finished
  ```json
  LessonId: isComplete
         1: 1
         2: 0
         3: 0
  ```
  **1/3 = 33% Course Progress**
