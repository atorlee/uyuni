/**
 * Copyright (c) 2020 SUSE LLC
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
package com.suse.manager.model.maintenance;

import com.redhat.rhn.domain.BaseDomainHelper;
import com.redhat.rhn.domain.org.Org;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "suseMaintenanceCalendar")
public class MaintenanceCalendar extends BaseDomainHelper {
    private Long id;
    private Org org;
    private String label;
    private String url;
    private String ical;

    /**
     * @return the id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mtcal_seq")
    @SequenceGenerator(name = "mtcal_seq", sequenceName = "suse_mtcal_id_seq", allocationSize = 1)
    public Long getId() {
        return id;
    }

    /**
     * @return the organization
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id", nullable = false)
    public Org getOrg() {
        return org;
    }

    /**
     * @return the label
     */
    @Column(name = "label")
    public String getLabel() {
        return label;
    }

    /**
     * @return the url
     */
    @Column(name = "url")
    protected String getUrl() {
        return url;
    }

    /**
     * @return the url as optional
     */
    @Transient
    public Optional<String> getUrlOpt() {
        return Optional.ofNullable(url);
    }

    /**
     * @return return the ical data
     */
    @Column(name = "ical")
    public String getIcal() {
        return ical;
    }

    /**
     * Set the id
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Set the org
     * @param org the org
     */
    public void setOrg(Org org) {
        this.org = org;
    }

    /**
     * Set the label
     * @param label the label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Set the Url
     * @param url the url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Set the ical calender data
     * @param ical the calender data
     */
    public void setIcal(String ical) {
        this.ical = ical;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MaintenanceCalendar that = (MaintenanceCalendar) o;

        return new EqualsBuilder()
                .append(org, that.org)
                .append(label, that.label)
                .append(ical, that.ical)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(org)
                .append(label)
                .append(ical)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("org", org)
                .append("label", label)
                .append("url", url)
                .toString();
    }
}
