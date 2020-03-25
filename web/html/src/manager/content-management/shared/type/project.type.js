// @flow

export type ProjectHistoryEntry = {
  version: number,
  message: string
}

export type ProjectPropertiesType = {
  label: string,
  name: string,
  description?: string,
  historyEntries: Array<ProjectHistoryEntry>,
}

export type ProjectSoftwareSourceType = {
  channelId: number,
  name: string,
  label: string,
  state: string,
  type: string,
}

export type ProjectEnvironmentType = {
  projectLabel: string,
  label: string,
  name: string,
  description: string,
  status: string,
  version: number,
  builtTime: ?string
}

export type ProjectFilterServerType = {
  id: number,
  name: string,
  criteriaKey: string,
  criteriaValue: string,
  entityType: string,
  rule: "deny" | "allow",
  state: string,
}

export type ProjectMessageType = {
  text: string,
  type: "info" | "warn" | "alert"
}


export type ProjectType = {
  properties: ProjectPropertiesType,
  softwareSources: Array<ProjectSoftwareSourceType>,
  filters: Array<any>,
  environments: Array<ProjectEnvironmentType>,
  messages: Array<ProjectMessageType>
}
