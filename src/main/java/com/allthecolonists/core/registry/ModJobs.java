public static final DeferredHolder<JobEntry, JobEntry> MEKANISM =
    JOBS.register(
        "mekanism",
        () -> new JobEntry.Builder()
            .setJobClass(JobMekanism.class)
            .createJobEntry()
    );