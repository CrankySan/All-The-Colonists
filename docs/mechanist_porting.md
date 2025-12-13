# Porting MineColonies' Mechanist to the Mekanism Worker Hut

The MineColonies jar bundled in `libs/` already contains the assets and classes for the **Mechanist/Mechanic** worker. To reuse that behavior for the Mekanism worker hut in this project, copy the Mechanist pipeline and rename it to the Mekanism worker equivalents.

## Key classes and assets in MineColonies

These entries come from `libs/minecolonies-1.1.1223-1.21.1-snapshot.jar` and provide the baseline to copy:

- `com.minecolonies.core.colony.jobs.JobMechanic` (worker logic and job registration)
- `com.minecolonies.core.colony.buildings.workerbuildings.BuildingMechanic` (hut/server side container and crafting modules)
- `com.minecolonies.core.blocks.huts.BlockHutMechanic` (hut block definition)
- `com.minecolonies.core.entity.ai.workers.crafting.EntityAIWorkMechanic` (AI routine)
- `com.minecolonies.core.client.model.MaleMechanistModel` and `FemaleMechanistModel` (citizen model overrides)
- Blueprint files under `blueprints/minecolonies/**/craftsmanship/metallurgy/mechanic*.blueprint` (structure levels)

Use these as the source when duplicating logic or assets.

## How to create the Mekanism variants

1. **Copy and rename the classes**: Create `JobMekanismWorker`, `BuildingMekanismWorker`, `BlockHutMekanismWorker`, and an AI class (e.g., `EntityAIWorkMekanismWorker`) using the Mechanist implementations as a template. Replace any references to `Mechanic`/`Mechanist` with `Mekanism` and adjust imports to match this mod's package (`com.allthecolonists`).
2. **Register the hut block**: Wire the renamed hut block through `ModBlocks` just like `BLOCKHUTMEKANISM`, and connect a matching block item and creative tab entry.
3. **Register the job**: Fill `ModJobs` with a new entry to register `JobMekanismWorker` once a 1.21.1 `JobRegistry` replacement is available.
4. **Hook up the hut type**: Once MineColonies exposes the hut type API for 1.21.1, mirror `BlockHutMechanic`'s registration path inside `ModHuts` to bind the Mekanism hut to its job and building class.
5. **Blueprints**: Copy the five `mechanic*.blueprint` files into `src/main/resources/blueprints/<your_style>/mekanism/` and adjust their `pack.json` entries to reference the new path and hut name.
6. **Language & models**: Add `en_us.json` strings for the Mekanism worker and point the block/item models to `assets/allthecolonists/models/block/huts/mekanism_hut.json` (already present). Duplicate or recolor the Mechanist citizen textures if you want distinct clothing.
7. **Testing loop**: Ensure the hut block places, the hut GUI binds to the Mekanism building class, and the worker hires correctly by checking the job assignment screen in-game.

## Current scaffolding in this repo

- `ModBlocks` already registers `BLOCKHUTMEKANISM` and its item.
- `MekanismWorkerHutBlock`, `ModJobs`, `ModHuts`, and `MekanismWorker` exist as stubs and should be filled using the Mechanist copies above once the MineColonies 1.21.1 APIs are available.

Keeping these steps in one place should make it straightforward to transplant MineColonies' Mechanist functionality to the Mekanism worker hut when the upstream APIs stabilize.
