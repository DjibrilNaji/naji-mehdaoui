const routes = {
  home: "/",
  parties: "/parties",
  party: (slug: string) => `/parties/${slug}`,
  api: {
    parties: {
      get: "/parties",
      getById: (id: string) => `/parties/${id}`,
      post: "/parties",
      put: (id: string) => `/parties/${id}`,
      delete: (id: string) => `/parties/${id}`
    }
  }
}

export default routes
