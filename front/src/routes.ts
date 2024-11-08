const routes = {
  home: "/",
  parties: "/parties",
  party: (slug: string) => `/parties/${slug}`
}

export default routes
