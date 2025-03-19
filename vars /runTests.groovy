def call(String pytestHtmlReport) {
    sh ". venv/bin/activate && pytest --html=${pytestHtmlReport} || true"
}
