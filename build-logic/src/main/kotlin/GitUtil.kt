/*
 * ALMPP - The advanced lightweight punishment plugin for Minecraft servers
 * Copyright (C) 2022 Coadon
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

import net.kyori.indra.git.IndraGitExtension
import org.eclipse.jgit.revwalk.RevWalk
import org.gradle.api.Project
import org.gradle.kotlin.dsl.findByType

object GitUtil {
    @JvmStatic
    fun commitsSinceLastTag(project: Project): Int? {
        val indraGit = project.extensions.findByType(IndraGitExtension::class)?.takeIf {
            it.isPresent && it.tags().isNotEmpty()
        } ?: return null
        val git = indraGit.git() ?: return null

        val tags = indraGit.tags()
        var depth = 0
        val walk = RevWalk(git.repository)
        var commit = walk.parseCommit(indraGit.commit())
        while (true) {
            for (tag in tags) {
                if (walk.parseCommit(tag.leaf.objectId) == commit) {
                    walk.dispose()
                    return depth
                }
            }
            depth++
            commit = walk.parseCommit(commit.parents[0])
        }
    }
}