const routes = {
  home: "/",
  parties: "/parties",
  addParty: "/parties/add",
  party: (slug: string) => `/parties/${slug}`,
  api: {
    parties: {
      get: "/parties",
      getById: (id: string) => `/parties/${id}`,
      post: "/parties",
      put: (id: string) => `/parties/${id}`,
      delete: (id: string) => `/parties/${id}`
    },
    addresses: {
      get: "/addresses"
    }
  }
}

export default routes
