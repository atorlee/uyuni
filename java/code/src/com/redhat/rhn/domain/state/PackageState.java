/**
 * Copyright (c) 2015 SUSE LLC
 *
 * This software is licensed to you under the GNU General Public License,
 * version 2 (GPLv2). There is NO WARRANTY for this software, express or
 * implied, including the implied warranties of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. You should have received a copy of GPLv2
 * along with this software; if not, see
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.txt.
 *
 * Red Hat trademarks are not licensed under GPLv2. No permission is
 * granted to use or replicate Red Hat trademarks that are incorporated
 * in this software or its documentation.
 */
package com.redhat.rhn.domain.state;

import com.redhat.rhn.domain.rhnpackage.PackageArch;
import com.redhat.rhn.domain.rhnpackage.PackageEvr;
import com.redhat.rhn.domain.rhnpackage.PackageName;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Represent the state of a single package: includes the package NEVRA (name, evr and
 * architecture), as well the state together with a version constraint.
 */
public class PackageState {

    private Long id;
    private PackageName name;
    private PackageEvr evr;
    private PackageArch arch;
    private long groupId;

    // IDs of enum values
    private int packageStateId;
    private int versionConstraintId;

    /**
     * @return the arch
     */
    public PackageArch getArch() {
        return arch;
    }

    /**
     * @param archIn the arch to set
     */
    public void setArch(PackageArch archIn) {
        this.arch = archIn;
    }

    /**
     * @return the evr
     */
    public PackageEvr getEvr() {
        return evr;
    }

    /**
     * @param evrIn the evr to set
     */
    public void setEvr(PackageEvr evrIn) {
        this.evr = evrIn;
    }

    /**
     * @return he id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param idIn the id to set
     */
    public void setId(Long idIn) {
        this.id = idIn;
    }

    /**
     * @return the name
     */
    public PackageName getName() {
        return name;
    }

    /**
     * @param nameIn the name to set.
     */
    public void setName(PackageName nameIn) {
        this.name = nameIn;
    }

    /**
     * @return the groupId
     */
    public Long getGroupId() {
        return groupId;
    }

    /**
     * @param groupIdIn
     */
    public void setGroupId(Long groupIdIn) {
        this.groupId = groupIdIn;
    }

    /**
     * @return the packageStateId
     */
    public int getPackageStateId() {
        return packageStateId;
    }

    /**
     * @param packageStateIdIn
     */
    public void setPackageStateId(int packageStateIdIn) {
        this.packageStateId = packageStateIdIn;
    }

    /**
     * Return the package state.
     *
     * @return the packageState
     */
    public PackageStates getPackageState() {
        return PackageStates.byId(packageStateId).
                orElseGet(() -> { throw new RuntimeException("Invalid package state"); });
    }

    /**
     * @param packageState
     */
    public void setPackageState(PackageStates packageState) {
        this.packageStateId = packageState.ID;
    }

    /**
     * @return the versionConstraint
     */
    public int getVersionConstraintId() {
        return versionConstraintId;
    }

    /**
     * @param versionConstraintIn the versionConstraint to set
     */
    public void setVersionConstraintId(int versionConstraintIdIn) {
        this.versionConstraintId = versionConstraintIdIn;
    }

    /**
     * @return the versionConstraint
     */
    public VersionConstraints getVersionConstraint() {
        return VersionConstraints.byId(versionConstraintId).
                orElseGet(() -> { throw new RuntimeException("Invalid constraint"); });
    }

    /**
     * @param versionConstraint
     */
    public void setVersionConstraint(VersionConstraints versionConstraint) {
        versionConstraintId = versionConstraint.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof PackageState)) {
            return false;
        }
        PackageState otherPackageState = (PackageState) other;
        return new EqualsBuilder()
                .append(getName(), otherPackageState.getName())
                .append(getEvr(), otherPackageState.getEvr())
                .append(getArch(), otherPackageState.getArch())
                .append(getGroupId(), otherPackageState.getGroupId())
                .append(getPackageStateId(), otherPackageState.getPackageStateId())
                .append(getVersionConstraintId(), otherPackageState.getVersionConstraintId())
                .isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(getName())
                .append(getEvr())
                .append(getArch())
                .append(getGroupId())
                .append(getPackageStateId())
                .append(getVersionConstraintId())
                .toHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", getId())
                .append("name", getName())
                .append("evr", getEvr())
                .append("arch", getArch())
                .append("groupId", getGroupId())
                .append("packageState", getPackageState())
                .append("versionConstraint", getVersionConstraint())
                .toString();
    }
}
